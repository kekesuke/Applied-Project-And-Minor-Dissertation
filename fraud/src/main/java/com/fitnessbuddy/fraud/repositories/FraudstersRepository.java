package com.fitnessbuddy.fraud.repositories;

import com.fitnessbuddy.fraud.models.Fraudsters;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FraudstersRepository extends JpaRepository<Fraudsters, Integer> {
    Optional<Fraudsters> findByEmailAddress(String email);
    Optional<Fraudsters> findByIpAddress(String email);
    Boolean existsByIpAddress(String ipAdress);
    Boolean existsByemailAddress(String email);
}
