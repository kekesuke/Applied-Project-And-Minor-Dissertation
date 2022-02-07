package com.fitnessbuddy.fraud.services;

import com.fitnessbuddy.fraud.repositories.FraudCheckHistoryRepository;
import com.fitnessbuddy.fraud.models.FraudCheckHistory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudCustomer(Long userId){
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(userId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return  false;
    }
}
