package org.group.smart_city.Respository;

import org.group.smart_city.Entities.Group;
import org.group.smart_city.Entities.ServiceProvider;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository  extends MongoRepository<Group,String> {
    public Group findGroupById(String Id);

    public Group findGroupByServiceProvider(ServiceProvider service);
}
