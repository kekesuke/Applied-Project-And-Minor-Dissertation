package com.fitnessbuddy.clients.notification.response;

public record NotificationRequest(
        long toUserId,
        String toUserName,
        String message
) {
}
