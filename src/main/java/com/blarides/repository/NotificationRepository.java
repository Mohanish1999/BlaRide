package com.blarides.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.blarides.domain.entity.Notification;

import javax.transaction.Transactional;

@Transactional
public interface NotificationRepository extends CrudRepository<Notification, Long> {
    @Modifying
    @Transactional
    @Query("delete from Notification u where u.notificationId = ?1")
    void deleteByNotificationId(Long notificationId);
}
