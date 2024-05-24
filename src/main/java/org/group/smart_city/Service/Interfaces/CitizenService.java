package org.group.smart_city.Service.Interfaces;

import org.group.smart_city.Dto.CitizenDto;
import org.group.smart_city.Entities.Citizen;

import java.util.List;


public interface CitizenService {
    public Citizen GetById(String id);
    public Citizen Authenticate(CitizenDto citizenDto);
    public Citizen Create(CitizenDto citizenDto);
    public Citizen Update(CitizenDto citizenDto);
    public void Delete(String id);
    public List<Citizen> GetAll();
}
