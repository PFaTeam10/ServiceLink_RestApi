package org.group.smart_city.Service.Interfaces;

import org.group.smart_city.Dto.ServiceProviderDto;
import org.group.smart_city.Entities.ServiceProvider;

import java.util.List;

public interface ServiceProviderService {
    ServiceProvider create(ServiceProviderDto serviceProviderDto);
    ServiceProvider getById(String id);
    ServiceProvider Update(String id, ServiceProviderDto serviceProviderDto);
    void Delete(String id);
    List<ServiceProvider> getAll();
}
