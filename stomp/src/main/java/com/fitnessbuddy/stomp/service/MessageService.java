package com.fitnessbuddy.stomp.service;

import com.fitnessbuddy.stomp.model.MessageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageService {

    @Autowired
    private SimpMessagingTemplate simp;


    public void Message(String message){
        log.debug("======================");
        simp.convertAndSend("/topic/chat", new MessageModel(message));
        log.debug("======================");
    }



}
