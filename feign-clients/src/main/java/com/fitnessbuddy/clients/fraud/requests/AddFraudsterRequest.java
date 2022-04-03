package com.fitnessbuddy.clients.fraud.requests;

public record AddFraudsterRequest(
        String ipAddress,
        String emailAddress
) {
}
