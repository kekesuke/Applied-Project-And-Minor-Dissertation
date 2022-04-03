package com.fitnessbuddy.fraud.controllers;

import com.fitnessbuddy.clients.fraud.response.FraudCheckResponse;
import com.fitnessbuddy.fraud.services.FraudCheckService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api")
@AllArgsConstructor
public class FraudCheckController {

    private final FraudCheckService fraudCheckService;

    @RequestMapping(method=RequestMethod.GET, path = "/fraud-check")
    FraudCheckResponse isFraud(@RequestParam(value="emailAddress") String emailAddress,
                               @RequestParam(value="ipAddress") String ipAddress){
        boolean isFraud = fraudCheckService.isFraudCustomer(emailAddress, ipAddress);
        log.info("fraud checking for customer {} {}", emailAddress, ipAddress);
        return new FraudCheckResponse(isFraud);

    }



}
