package com.blarides.service.ServiceImpl;

import com.blarides.domain.entity.BookingReference;
import com.blarides.domain.entity.Notification;
import com.blarides.domain.entity.User;
import com.blarides.repository.NotificationRepository;
import com.blarides.service.NotificationService;
import com.blarides.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private UserService userService;

    private NotificationRepository notificationRepository;

    @Autowired
    public NotificationServiceImpl(UserService userService, NotificationRepository notificationRepository) {
        this.userService = userService;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void sendAcceptedNotification(BookingReference bookingReference, User user) {

        User author = userService.findByUsername(bookingReference.getAuthor());
        Notification notification = new Notification();
        notification.setUser(author);
        notification.setAuthor(user.getUsername());
        notification.setBookingReference(bookingReference);
        notification.setContent(String.format("%s has accepted your booking", user.getUsername()));
        notificationRepository.save(notification);
    }

    @Override
    public void sendCancelledNotification(BookingReference bookingReference, User user) {

        User author = userService.findByUsername(bookingReference.getAuthor());
        Notification notification = new Notification();
        notification.setUser(author);
        notification.setAuthor(user.getUsername());
        notification.setBookingReference(bookingReference);
        notification.setContent(String.format("%s has cancelled your booking", user.getUsername()));
        notificationRepository.save(notification);
    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepository.deleteByNotificationId(id);
    }
}
