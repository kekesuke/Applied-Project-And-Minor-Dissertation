package com.fitnessbuddy.clients.diet;

import com.fitnessbuddy.clients.notification.response.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("diet")
public interface DietClient {
    @PostMapping("api/diet")
    void getFoodIntake(NotificationRequest notificationRequest);
}

