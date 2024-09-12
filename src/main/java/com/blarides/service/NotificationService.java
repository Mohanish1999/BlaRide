package com.blarides.service;

import com.blarides.domain.entity.BookingReference;
import com.blarides.domain.entity.User;

public interface NotificationService {
    void sendAcceptedNotification(BookingReference bookingReference, User user);
    void sendCancelledNotification(BookingReference bookingReference, User user);
    void deleteNotification(Long id);
}
