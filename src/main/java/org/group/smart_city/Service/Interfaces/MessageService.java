package org.group.smart_city.Service.Interfaces;


import org.group.smart_city.Dto.GroupDto;
import org.group.smart_city.Dto.MessageDto;
import org.group.smart_city.Entities.Citizen;
import org.group.smart_city.Entities.Group;
import org.group.smart_city.Entities.Message;

import java.util.List;

public interface MessageService {

    public List<Message> GetByIdGroup(Group group);
    public Message Create(MessageDto messageDto);
    public List<Message> GetAllByCitizen(Citizen citizen);

    List<Message> GetAllByGroup(Group group);
}
