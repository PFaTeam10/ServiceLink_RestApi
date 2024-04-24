package org.group.smart_city.Service.Implementations;

import org.group.smart_city.Dto.ReclamationDto;
import org.group.smart_city.Dto.ServiceProviderDto;
import org.group.smart_city.Entities.Reclamation;
import org.group.smart_city.Entities.ServiceProvider;
import org.group.smart_city.Exceptions.AppException;
import org.group.smart_city.Response.ErrorMessages;
import org.group.smart_city.Respository.ReclamationRepository;
import org.group.smart_city.Respository.ServiceProviderRepository;
import org.group.smart_city.Service.Interfaces.ServiceProviderService;
import org.group.smart_city.Utils.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ServiceProviderImp implements ServiceProviderService {

    @Autowired
    ServiceProviderRepository serviceProviderRepository;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public ServiceProvider create(ServiceProviderDto serviceProviderDto) {
        if (serviceProviderDto == null) {
            throw new IllegalArgumentException("serviceProviderDto must not be null");
        }
        ServiceProvider serviceProvider = modelMapper.map(serviceProviderDto, ServiceProvider.class);
        if (serviceProvider == null) {
            throw new AppException("Mapping from serviceProviderDto to serviceProvider failed");
        }

        return serviceProviderRepository.save(serviceProvider);
    }

    @Override
    public ServiceProvider getById(String id) {
        return serviceProviderRepository.findById(id).orElseThrow(()
                ->  new AppException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
    }

    @Override
    public ServiceProvider Update(String id, ServiceProviderDto serviceProviderDto) {
        ServiceProvider serviceProvider = serviceProviderRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));

        // Update project object with fields from projectDto
        modelMapper.map(serviceProviderDto, serviceProvider);
        return serviceProvider;
    }

    @Override
    public void Delete(String id) {
        if(this.getById(id) != null)
            serviceProviderRepository.deleteById(id);
    }
}
