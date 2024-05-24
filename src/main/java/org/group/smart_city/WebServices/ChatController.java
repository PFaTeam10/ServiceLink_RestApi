package org.group.smart_city.WebServices;

import org.group.smart_city.Dto.GroupDto;
import org.group.smart_city.Dto.MessageDto;
import org.group.smart_city.Entities.Group;
import org.group.smart_city.Entities.Message;
import org.group.smart_city.Entities.ServiceProvider;
import org.group.smart_city.Service.Interfaces.MessageService;
import org.group.smart_city.Service.Interfaces.ServiceProviderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ServiceProviderService serviceProviderService;
    @Autowired
    ModelMapper modelMapper = new ModelMapper();
    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receiveMessage(@Payload MessageDto messageDto){
        System.out.println("receiveMessage : "+messageDto);
        if(messageDto.getMessage()!=null){
            Message save = messageService.Create(messageDto); 
            return save;
        }
        return null;
    }
    @MessageMapping("/join")
    @SendTo("/chatroom/public")
    public List<Message> join(@Payload String id){
        System.out.println("join id service : "+id);
        ServiceProvider serviceProvider = serviceProviderService.getById(id);
        System.out.println("serviceProvider : "+serviceProvider);
        List<Message> messageList = messageService.GetAllByServiceProvider(serviceProvider);

        return messageList;
    }

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getCitizen().getId(),"/private",message);
        System.out.println("Payload private : "+message.toString());
        return message;
    }
}
