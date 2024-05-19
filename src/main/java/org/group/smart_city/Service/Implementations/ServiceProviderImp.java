package org.group.smart_city.Service.Implementations;

import org.group.smart_city.Dto.CitizenDto;
import org.group.smart_city.Dto.ReclamationDto;
import org.group.smart_city.Dto.ServiceProviderDto;
import org.group.smart_city.Entities.Citizen;
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
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ServiceProviderImp implements ServiceProviderService {

    @Autowired
    ServiceProviderRepository serviceProviderRepository;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public ServiceProvider Create(ServiceProviderDto serviceProviderDto) {
        System.out.println(serviceProviderDto);
        ServiceProvider serviceProvider = modelMapper.map(serviceProviderDto, ServiceProvider.class);
        String hashedPassword = BCrypt.hashpw(serviceProvider.getPassword(),BCrypt.gensalt());
        serviceProvider.setPassword(hashedPassword);
        return serviceProviderRepository.save(serviceProvider);
    }
    @Override
    public ServiceProvider Authenticate(ServiceProviderDto serviceProviderDto) {
        ServiceProvider serviceProvider   = serviceProviderRepository.findServiceProviderByEmail(serviceProviderDto.getEmail());
        System.out.println("serviceProviderDto : " + serviceProviderDto);
        if (serviceProvider == null) {
            throw new AppException("No User found with this email");
        }

        if (BCrypt.checkpw(serviceProviderDto.getPassword(), serviceProvider.getPassword())) {
            return serviceProvider;
        }
        throw new AppException("Password Incorrect");
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
    @Override
    public List<ServiceProvider> getAll() {
        System.out.println("getting all service providers");
        return serviceProviderRepository.findAll();
    }
}
