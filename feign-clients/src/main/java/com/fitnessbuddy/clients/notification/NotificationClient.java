package com.fitnessbuddy.clients.notification;

import com.fitnessbuddy.clients.notification.response.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("notification")
public interface NotificationClient {
    @PostMapping("api/notification")
    void sendNotification(NotificationRequest notificationRequest);
}
