package com.fitnessbuddy.stomp.repositories;

import com.fitnessbuddy.stomp.model.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface RoomRepository extends JpaRepository<RoomModel, String> {
    Optional<RoomModel> findBySenderIdAndRecipientId(String sentId, String receivedId);
}
