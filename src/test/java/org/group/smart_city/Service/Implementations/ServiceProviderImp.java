package org.group.smart_city.Service.Implementations;

import org.group.smart_city.Dto.ServiceProviderDto;
import org.group.smart_city.Entities.ServiceProvider;
import org.group.smart_city.Service.Interfaces.ServiceProviderService;
import org.springframework.stereotype.Service;

@Service

public class ServiceProviderImp implements ServiceProviderService {

    @Override
    public ServiceProvider create(ServiceProviderDto serviceProviderDto) {
        return null;
    }

    @Override
    public ServiceProvider getById(String id) {
        return null;
    }

    @Override
    public ServiceProvider Update(String id, ServiceProviderDto serviceProviderDto) {
        return null;
    }

    @Override
    public void Delete(String id) {

    }
}
