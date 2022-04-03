package com.fitnessbuddy.fraud.services;

import com.fitnessbuddy.fraud.repositories.FraudCheckHistoryRepository;
import com.fitnessbuddy.fraud.models.FraudCheckHistory;
import com.fitnessbuddy.fraud.repositories.FraudstersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;
    private final FraudstersRepository fraudstersRepository;

    public boolean isFraudCustomer(String emailAddress, String ipAddress){
        boolean isFound = false;

        if(fraudstersRepository.findByIpAddress(ipAddress).isPresent() || fraudstersRepository.findByEmailAddress(emailAddress).isPresent())
        {
            isFound = true;
        }

        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .isFraudster(isFound)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return isFound;
    }
}
