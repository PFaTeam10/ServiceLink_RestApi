package org.group.smart_city.Respository;


import org.group.smart_city.Entities.Citizen;
import org.group.smart_city.Entities.Reclamation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReclamationRepository extends MongoRepository<Reclamation,String> {
    List<Reclamation> findAllByServiceProviderId(String id);
    List<Reclamation> findAllByCitizenId(String id);
}
