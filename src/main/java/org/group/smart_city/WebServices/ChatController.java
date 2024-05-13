package org.group.smart_city.WebServices;

import org.group.smart_city.Dto.MessageDto;
import org.group.smart_city.Entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public MessageDto receiveMessage(@Payload MessageDto messageDto){
        System.out.println("Payload  public : "+messageDto.toString());

        return messageDto;
    }

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getCitizen().getId(),"/private",message);
        System.out.println("Payload private : "+message.toString());
        return message;
    }
}
