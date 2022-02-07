package com.fitnessbuddy.clients.fraud;

import com.fitnessbuddy.clients.fraud.response.FraudCheckResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("fraud")
public interface FraudClient {
    @GetMapping(path = "api/fraud-check/{userId}")
    FraudCheckResponse isFraud(@PathVariable("userId") Long userId);

}
