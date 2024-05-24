package org.group.smart_city.Service.Interfaces;

import org.group.smart_city.Dto.ServiceTypeDto;
import org.group.smart_city.Entities.Reclamation;
import org.group.smart_city.Entities.ServiceType;

import java.util.List;

public interface ServiceTypeService {
    ServiceType create(ServiceTypeDto serviceTypeDto);
    ServiceType getById(String id);
    ServiceType Update(String id, ServiceTypeDto serviceTypeDto);
    void Delete(String id);
}
