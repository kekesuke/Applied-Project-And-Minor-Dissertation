package com.fitnessbuddyapi.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.fitnessbuddyapi.models.MessageModel;
import com.fitnessbuddyapi.payload.response.MessageResponse;

@Controller
public class MessageController {
	
	@MessageMapping("/chat")
	@SendTo("/topic/chat")
	public MessageResponse getMessage( MessageModel message) {
		return new MessageResponse(message.getMessage() + "hello");
	}
	
	
}

