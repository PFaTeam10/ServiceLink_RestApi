package org.group.smart_city.Service.Implementations;

import org.group.smart_city.Dto.ReclamationDto;
import org.group.smart_city.Entities.Reclamation;
import org.group.smart_city.Exceptions.AppException;
import org.group.smart_city.Response.ErrorMessages;
import org.group.smart_city.Respository.ReclamationRepository;
import org.group.smart_city.Service.Interfaces.ServiceTypeService;
import org.group.smart_city.Utils.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ServiceTypeImp implements ServiceTypeService {
    @Autowired
    ReclamationRepository reclamationRepository;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public Reclamation create(ReclamationDto reclamationDto) {
        if (reclamationDto == null) {
            throw new IllegalArgumentException("reclamationDto must not be null");
        }
        Reclamation reclamation = modelMapper.map(reclamationDto, Reclamation.class);
        if (reclamation == null) {
            throw new AppException("Mapping from reclamationDto to reclamation failed");
        }

        return reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation getById(String id) {
        return reclamationRepository.findById(id).orElseThrow(()
                ->  new AppException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
    }

    @Override
    public Reclamation Update(String id, ReclamationDto reclamationDto) {
        Reclamation reclamation = reclamationRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));

        // Update project object with fields from projectDto
        modelMapper.map(reclamationDto, reclamation);
        return reclamation;
    }

    @Override
    public void Delete(String id) {
        if(this.getById(id) != null)
            reclamationRepository.deleteById(id);
    }
}
