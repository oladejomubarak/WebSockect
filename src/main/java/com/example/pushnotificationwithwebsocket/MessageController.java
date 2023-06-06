package com.example.pushnotificationwithwebsocket;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/application")
    @SendTo("/all/messages")
    public Message send(final Message message) throws Exception{
        return message;
    }
    @MessageMapping("/private")
    public void sendToSpecificUser(@Payload Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getTo(), "/specific", message);
    }

}
