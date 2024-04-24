package org.group.smart_city.Service.Implementations;

import org.group.smart_city.Dto.ServiceTypeDto;
import org.group.smart_city.Entities.ServiceType;
import org.group.smart_city.Service.Interfaces.ServiceTypeService;
import org.springframework.stereotype.Service;

@Service

public class ServiceTypeImp implements ServiceTypeService {
    @Override
    public ServiceType create(ServiceType serviceType) {
        return null;
    }

    @Override
    public ServiceType getById(String id) {
        return null;
    }

    @Override
    public ServiceType Update(String id, ServiceTypeDto serviceTypeDto) {
        return null;
    }

    @Override
    public void Delete(String id) {

    }
}
