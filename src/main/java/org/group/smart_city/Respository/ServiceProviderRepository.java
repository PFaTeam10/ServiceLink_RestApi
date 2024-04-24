package org.group.smart_city.Respository;

import org.group.smart_city.Entities.ServiceProvider;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceProviderRepository extends MongoRepository<ServiceProvider,String>{
}
