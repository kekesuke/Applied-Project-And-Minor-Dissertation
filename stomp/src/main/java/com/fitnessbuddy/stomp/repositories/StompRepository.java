package com.fitnessbuddy.stomp.repositories;

import com.fitnessbuddy.stomp.model.MessageModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StompRepository extends JpaRepository<MessageModel, Integer> {
}
