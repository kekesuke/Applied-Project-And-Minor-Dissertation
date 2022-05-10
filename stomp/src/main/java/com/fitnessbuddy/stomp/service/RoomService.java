package com.fitnessbuddy.stomp.service;

import com.fitnessbuddy.stomp.model.RoomModel;
import com.fitnessbuddy.stomp.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepo;

    public Optional<String> findById(String sentId, String receivedId, boolean exists) {
        return roomRepo.findBySenderIdAndRecipientId(sentId, receivedId).map(RoomModel::getChatId).or(() -> {
            if (exists == false) {
                return Optional.empty();
            }


            var chatId = String.format("%s_%s", sentId, receivedId);

            RoomModel senderToReciever = RoomModel
                    .builder()
                    .chatId(chatId)
                    .senderId(String.valueOf(sentId))
                    .recipientId(String.valueOf(receivedId))
                    .build();

            RoomModel RecieverToSender = RoomModel
                    .builder()
                    .chatId(chatId)
                    .senderId(String.valueOf(receivedId))
                    .recipientId(String.valueOf(sentId))
                    .build();
            roomRepo.save(senderToReciever);
            roomRepo.save(RecieverToSender);

            return Optional.of(chatId);
        });
    }
}



