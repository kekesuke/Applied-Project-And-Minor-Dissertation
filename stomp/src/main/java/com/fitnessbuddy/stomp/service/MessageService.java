package com.fitnessbuddy.stomp.service;

import com.fitnessbuddy.stomp.model.MessageModel;
import com.fitnessbuddy.stomp.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepo;
    @Autowired
    private RoomService roomService;

    public MessageModel save(MessageModel chatMessage){
        messageRepo.save(chatMessage);
        return chatMessage;
    }

    public long countMessages(String sendId, String receiveId){
        return  messageRepo.count(sendId,receiveId);
    }

    public List<MessageModel> findMessages(String sendId, String receiveId){
        var chatId = roomService.findById(sendId, receiveId, false);
        var messageList = chatId.map(cId -> messageRepo.findByIdChat(cId)).orElse(new ArrayList<>());


        return messageList;
    }

    public MessageModel findById(String id) throws Exception {
        return messageRepo
                .findById(id).map(chatModel -> {
                    return messageRepo.save(chatModel);
                })
                .orElseThrow(() ->
                        new Exception("message not found"));
    }

}
