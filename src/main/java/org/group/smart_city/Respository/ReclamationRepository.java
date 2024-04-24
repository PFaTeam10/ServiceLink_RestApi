package org.group.smart_city.Respository;


import org.group.smart_city.Entities.Reclamation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamationRepository extends MongoRepository<Reclamation,String> {
}
