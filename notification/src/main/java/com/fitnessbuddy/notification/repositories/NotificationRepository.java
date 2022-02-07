package com.fitnessbuddy.notification.repositories;

import com.fitnessbuddy.notification.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
