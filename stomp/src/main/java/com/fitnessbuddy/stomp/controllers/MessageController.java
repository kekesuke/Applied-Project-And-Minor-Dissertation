package com.fitnessbuddy.stomp.controllers;

import com.fitnessbuddy.stomp.model.MessageModel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import com.fitnessbuddy.stomp.response.MessageResponse;

@Controller
public class MessageController {

    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    public MessageResponse getMessage(MessageModel message) {
        return new MessageResponse("message.getMessageContent()" + "hello");
    }


}
