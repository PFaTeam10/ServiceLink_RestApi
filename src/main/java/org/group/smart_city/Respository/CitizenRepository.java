package org.group.smart_city.Respository;

import org.group.smart_city.Dto.CitizenDto;
import org.group.smart_city.Entities.Citizen;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CitizenRepository  extends MongoRepository<Citizen,String> {
    public Citizen findCitizenById(String Id);
    public Citizen findCitizenByEmail(String email);
}
