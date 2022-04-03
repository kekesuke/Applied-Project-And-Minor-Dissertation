package com.fitnessbuddy.fraud.controllers;

import com.fitnessbuddy.clients.fraud.requests.AddFraudsterRequest;
import com.fitnessbuddy.fraud.services.FraudsterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("api/fraud")
@AllArgsConstructor
public class FraudsterController {

    private final FraudsterService fraudsterService;

    @PostMapping("/newFraudster")
    public ResponseEntity<?> addFraudster(@RequestBody AddFraudsterRequest addFraudsterRequest, String addedBy){
        return fraudsterService.addFraudster(addFraudsterRequest, addedBy);

    }



}


