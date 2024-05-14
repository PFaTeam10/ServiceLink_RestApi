package org.group.smart_city.Respository;

import org.group.smart_city.Dto.GroupDto;
import org.group.smart_city.Entities.Citizen;
import org.group.smart_city.Entities.Group;
import org.group.smart_city.Entities.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message,String> {
    public List<Message> getMessagesByGroup(Group group);

    public List<Message> findAllByCitizen(Citizen citizen);

}
