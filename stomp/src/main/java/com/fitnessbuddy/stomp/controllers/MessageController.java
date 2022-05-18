package com.fitnessbuddy.stomp.controllers;

import com.fitnessbuddy.stomp.model.MessageModel;
import com.fitnessbuddy.stomp.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    @MessageMapping("/hello-msg-mappung")
    @SendTo("/topic/greetings")
    public MessageModel echoMessageMapping(String message){
        log.debug("React to hello-msg-mapping");
        return new MessageModel(message);
    }

    @RequestMapping(value = "/hello-covert-and-send", method = RequestMethod.POST)
    public void echoConvertAndSend(@RequestParam("msg") String message){
        messageService.Message(message);
    }





}
