package org.group.smart_city.Service.Implementations;
import org.group.smart_city.Dto.MessageDto;
import org.group.smart_city.Entities.Group;
import org.group.smart_city.Entities.Message;
import org.group.smart_city.Exceptions.AppException;
import org.group.smart_city.Response.ApiResponse;
import org.group.smart_city.Respository.MessageRepository;
import org.group.smart_city.Service.Interfaces.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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


    public List<Message> GetByIdGroup(Group group) {
        // Retrieve Education from the database
        return messageRepository.findAllByGroup(group);
    }

    @Override
    public Message Create(MessageDto messageDto) {
        if (messageDto == null) {
            throw new IllegalArgumentException("EducationDto must not be null");
        }
        Message message = modelMapper.map(messageDto, Message.class);
        if (message == null) {
            throw new AppException("Mapping from EducationDto to Education failed");
        }
        System.out.print(message);
        return messageRepository.save(message);
    }


}
