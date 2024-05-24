package org.group.smart_city.Service.Implementations;
import org.group.smart_city.Dto.CitizenDto;
import org.group.smart_city.Dto.GroupDto;
import org.group.smart_city.Dto.MessageDto;
import org.group.smart_city.Entities.Citizen;
import org.group.smart_city.Entities.Group;
import org.group.smart_city.Entities.Message;
import org.group.smart_city.Entities.ServiceProvider;
import org.group.smart_city.Exceptions.AppException;
import org.group.smart_city.Response.ApiResponse;
import org.group.smart_city.Respository.MessageRepository;
import org.group.smart_city.Service.Interfaces.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class MessageServiceImp implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    ModelMapper modelMapper = new ModelMapper();


    public List<Message> GetByServiceProvider(ServiceProvider serviceProvider) {
        // Retrieve Education from the database
        return messageRepository.getMessagesByServiceProvider(serviceProvider);
    }



    //    public Message Create(MessageDto messageDto) {
//        if (messageDto == null) {
//            throw new IllegalArgumentException("messageDto must not be null");
//        }
//        System.out.print(messageDto);
//        Message message = modelMapper.map(messageDto, Message.class);
//        if (message == null) {
//            throw new AppException("Mapping from messageDto to message failed");
//        }
//        System.out.print(message);
//        return messageRepository.save(message);
//    }
    public Message Create(MessageDto messageDto) {
        if(!messageDto.isCitizenSender()){
            messageDto.setCitizen(null);
        }
        Message message = modelMapper.map(messageDto, Message.class);
        return messageRepository.save(message);
    }

    @Override
    public List<Message> GetAllByCitizen(Citizen citizen) {
        return messageRepository.findAllByCitizen(citizen);
    }


    @Override
    public List<Message> GetAllByServiceProvider(ServiceProvider serviceProvider)  {
        if (serviceProvider == null) {
            throw new AppException("serviceProvider is null");
        }
        return messageRepository.getMessagesByServiceProvider(serviceProvider);
    }

}
