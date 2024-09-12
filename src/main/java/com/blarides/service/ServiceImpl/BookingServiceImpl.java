package com.blarides.service.ServiceImpl;

import com.blarides.domain.constant.AccountType;
import com.blarides.domain.constant.BookingReferenceStatus;
import com.blarides.domain.entity.BookingReference;
import com.blarides.domain.entity.DriverAccount;
import com.blarides.domain.entity.RiderAccount;
import com.blarides.domain.entity.User;
import com.blarides.repository.BookingReferenceRepository;
import com.blarides.repository.UserRepository;
import com.blarides.service.BookingService;
import com.blarides.util.DateTimeConverterUtil;
import com.blarides.util.EmailNotificationUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private static final String[] MONTH_NAMES = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
    private static final String[] DAY_NAMES = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

    private final BookingReferenceRepository bookingReferenceRepository;
    private final UserRepository userRepository;
    private final DateTimeConverterUtil dateTimeConverterUtil;
    private final EmailNotificationUtil emailNotificationUtil;

    @Autowired
    public BookingServiceImpl(BookingReferenceRepository bookingReferenceRepository,
                              UserRepository userRepository,
                              DateTimeConverterUtil dateTimeConverterUtil,
                              EmailNotificationUtil emailNotificationUtil) {
        this.bookingReferenceRepository = bookingReferenceRepository;
        this.userRepository = userRepository;
        this.dateTimeConverterUtil = dateTimeConverterUtil;
        this.emailNotificationUtil = emailNotificationUtil;
    }

    @Override
    public void createBooking(BookingReference bookingReference, String source, String accountType, Principal principal) {
        Date date = dateTimeConverterUtil.StringToDateConverter(source);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        String month = MONTH_NAMES[calendar.get(Calendar.MONTH)];
        String dayOfWeek = DAY_NAMES[calendar.get(Calendar.DAY_OF_WEEK) - 1];
        String dayOfMonth = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateForSearch = formatter.format(date);

        String time = dateTimeConverterUtil.DateToTimeConverter(date);

        bookingReference.setDayOfMonth(dayOfMonth);
        bookingReference.setDayOfWeek(dayOfWeek);
        bookingReference.setMonth(month);
        bookingReference.setDate(date);
        bookingReference.setTime(time);
        bookingReference.setDateForSearch(dateForSearch);
        bookingReference.setAccountType(accountType);

        User user = userRepository.findByUsername(principal.getName());
        if (accountType.equalsIgnoreCase(AccountType.driverAccountType)) {
            DriverAccount driverAccount = user.getDriverAccount();
            bookingReference.setDriverAccount(driverAccount);
            bookingReference.setAuthor(driverAccount.getUsername());
        } else if (accountType.equalsIgnoreCase(AccountType.riderAccountType)) {
            RiderAccount riderAccount = user.getRiderAccount();
            List<RiderAccount> passengerList = new ArrayList<>();
            for (int i = 0; i < bookingReference.getPassengerNumber(); i++) {
                passengerList.add(riderAccount);
            }
            bookingReference.setPassengerList(passengerList);
            bookingReference.setAuthor(riderAccount.getUsername());
        }

        bookingReference.setBookingStatus(BookingReferenceStatus.PENDING);

        bookingReferenceRepository.save(bookingReference);
    }

    @Override
    public void acceptDriverBooking(User user, Integer seatsReserved, BookingReference bookingReference) {
        RiderAccount riderAccount = user.getRiderAccount();

        int availableSeats = bookingReference.getPassengerNumber();
        availableSeats -= seatsReserved;
        bookingReference.setPassengerNumber(availableSeats);

        List<RiderAccount> passengerList = bookingReference.getPassengerList();
        for (int i = 0; i < seatsReserved; i++) {
            passengerList.add(riderAccount);
        }
        bookingReference.setPassengerList(passengerList);
        bookingReference.setBookingStatus(BookingReferenceStatus.IN_PROGRESS);

        User author = userRepository.findByUsername(bookingReference.getAuthor());
        String[] emails = {user.getEmail(), author.getEmail()};
        bookingReferenceRepository.save(bookingReference);
        emailNotificationUtil.sendNotification(emails, bookingReference);
    }

    @Override
    public void acceptRiderBooking(User user, BookingReference bookingReference) {
        DriverAccount driverAccount = user.getDriverAccount();

        bookingReference.setDriverAccount(driverAccount);
        bookingReference.setBookingStatus(BookingReferenceStatus.IN_PROGRESS);

        User author = userRepository.findByUsername(bookingReference.getAuthor());
        String[] emails = {user.getEmail(), author.getEmail()};
        bookingReferenceRepository.save(bookingReference);
        emailNotificationUtil.sendNotification(emails, bookingReference);
    }

    @Override
    public BookingReference findBookingReference(Long id) {
        return bookingReferenceRepository.findById(id).orElse(null);
    }

    @Override
    public List<BookingReference> findAll() {
        return bookingReferenceRepository.findAll();
    }

    @Override
    public List<BookingReference> findByAccountType(String accountType) {
        return bookingReferenceRepository.findByAccountType(accountType);
    }

    @Override
    public BookingReference saveBooking(BookingReference bookingReference) {
        return bookingReferenceRepository.save(bookingReference);
    }
}
