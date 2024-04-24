package org.group.smart_city.Respository;

import org.group.smart_city.Entities.ServiceType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTypeRepository extends MongoRepository<ServiceType,String> {
}
