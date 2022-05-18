package com.fitnessbuddy.stomp.service;

import com.fitnessbuddy.stomp.model.MessageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MessageService {

    @Autowired
    private SimpMessagingTemplate simp;


    public void Message(String message){
        log.debug("Start convertAndSend ${new Date()}");
        simp.convertAndSend("/topic/greetings", new MessageModel(message));
        log.debug("End convertAndSend ${new Date()}");
    }



}
