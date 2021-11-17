package com.fitnessbuddyapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessbuddyapi.models.MessageModel;
import com.fitnessbuddyapi.repositories.UserRepository;

@RestController
public class MessageController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@MessageMapping("/chat/{to}")
	public void sendMessage(@DestinationVariable String to, MessageModel message) {
		System.out.println("Send message: " + message + " to:" + to);
		
		boolean userInDb = userRepo.existsByUsername(to);
		if(userInDb) {
			simpMessagingTemplate.convertAndSend("/topic.messages" + to,message);
		}
	}

}
