package com.fitnessbuddy.fraud.services;

import com.fitnessbuddy.clients.fraud.requests.AddFraudsterRequest;
import com.fitnessbuddy.fraud.models.Fraudsters;
import com.fitnessbuddy.fraud.repositories.FraudstersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class FraudsterService {

    private final FraudstersRepository fraudstersRepository;

    public ResponseEntity<?> addFraudster(AddFraudsterRequest addFraudsterRequest, String addedBy) {
       log.info(addFraudsterRequest.toString());
        if (fraudstersRepository.existsByIpAddress(addFraudsterRequest.ipAddress())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Ip address is already in DB!");
        }

        if (fraudstersRepository.existsByemailAddress(addFraudsterRequest.emailAddress())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in the DB");
        }

        fraudstersRepository.save(
                Fraudsters.builder()
                        .addedAt(LocalDateTime.now())
                        .addedBy(addedBy)
                        .emailAddress(addFraudsterRequest.emailAddress())
                        .ipAddress(addFraudsterRequest.ipAddress())
                        .build()
        );
        return ResponseEntity.ok("added successfully!");
    }
}
