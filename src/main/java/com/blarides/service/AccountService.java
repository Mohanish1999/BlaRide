package com.blarides.service;

import java.util.List;

import com.blarides.domain.entity.BookingReference;
import com.blarides.domain.entity.DriverAccount;
import com.blarides.domain.entity.RiderAccount;
import com.blarides.domain.entity.User;

public interface AccountService {
    DriverAccount createDriverAccount(User user);
    RiderAccount createRiderAccount(User user);
    List<BookingReference> getAccountBookingReference(String sort, String accountType, User user);
    List<BookingReference> getAccountSearchResult(String sort, String accountType, String date, String arrival, String departure, User user, String... passengerNumber);
}
