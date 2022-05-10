package com.fitnessbuddy.stomp.controllers;

import com.fitnessbuddy.stomp.model.MessageModel;
import com.fitnessbuddy.stomp.service.MessageService;
import com.fitnessbuddy.stomp.service.MessageService;
import com.fitnessbuddy.stomp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import com.fitnessbuddy.stomp.response.MessageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate msgTemp;
    @Autowired
    private MessageService messageService;
    @Autowired
    private RoomService roomService;

    @MessageMapping("/chat")
    public void message(@Payload MessageModel message){
        var chatId = roomService.findById(message.getSenderId(),message.getRecieverId(), true);
        message.setChatId(chatId.get());

        MessageModel savedMessage = messageService.save(message);
        msgTemp.convertAndSendToUser(message.getRecieverId(),"/queue/messages", savedMessage);
    }

    @GetMapping("/messages/{sendId}/recieveId}/count")
    public ResponseEntity<Long> countMessages (@PathVariable String sendId, @PathVariable String recipientId) {
        return ResponseEntity.ok(messageService.countMessages(sendId,recipientId));
    }

    @GetMapping("/messages/{sendId}/recieveId}")
    public ResponseEntity<?> findChatMessages (@PathVariable String sendId, @PathVariable String recipientId) {
        return ResponseEntity.ok(messageService.findMessages(sendId,recipientId));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessages (@PathVariable String id) throws Exception {
        return ResponseEntity.ok(messageService.findById(id));
    }



}
