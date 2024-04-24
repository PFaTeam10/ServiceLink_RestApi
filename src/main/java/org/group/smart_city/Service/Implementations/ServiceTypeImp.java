package org.group.smart_city.Service.Implementations;

import lombok.Data;
import org.group.smart_city.Dto.ReclamationDto;
import org.group.smart_city.Dto.ServiceTypeDto;
import org.group.smart_city.Entities.Reclamation;
import org.group.smart_city.Entities.ServiceType;
import org.group.smart_city.Exceptions.AppException;
import org.group.smart_city.Response.ErrorMessages;
import org.group.smart_city.Respository.ReclamationRepository;
import org.group.smart_city.Respository.ServiceTypeRepository;
import org.group.smart_city.Service.Interfaces.ServiceTypeService;
import org.group.smart_city.Utils.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ServiceTypeImp implements ServiceTypeService {
    @Autowired
    ServiceTypeRepository serviceTypeRepository;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public ServiceType create(ServiceTypeDto serviceTypeDto) {
        if (serviceTypeDto == null) {
            throw new IllegalArgumentException("serviceTypeDto must not be null");
        }
        ServiceType serviceType = modelMapper.map(serviceTypeDto, ServiceType.class);
        if (serviceType == null) {
            throw new AppException("Mapping from serviceTypeDto to ServiceType failed");
        }

        return serviceTypeRepository.save(serviceType);
    }

    @Override
    public ServiceType getById(String id) {
        return serviceTypeRepository.findById(id).orElseThrow(()
                ->  new AppException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
    }

    @Override
    public ServiceType Update(String id, ServiceTypeDto serviceTypeDto) {
        ServiceType serviceType = serviceTypeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));

        modelMapper.map(serviceTypeDto, serviceType);
        return serviceType;
    }

    @Override
    public void Delete(String id) {
        if(this.getById(id) != null)
            serviceTypeRepository.deleteById(id);
    }
}
