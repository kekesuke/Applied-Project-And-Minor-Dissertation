package com.fitnessbuddy.fraud.controllers;

import com.fitnessbuddy.clients.fraud.response.FraudCheckResponse;
import com.fitnessbuddy.fraud.services.FraudCheckService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/fraud-check")
@AllArgsConstructor
public class FraudCheckController {

    private final FraudCheckService fraudCheckService;

    @GetMapping(path = "{userId}")
    public FraudCheckResponse isFraud(@PathVariable("userId") Long userId){
        boolean isFraud = fraudCheckService.isFraudCustomer(userId);
        log.info("fraud checking for customer {}", userId);
        return new FraudCheckResponse(isFraud);

    }



}
