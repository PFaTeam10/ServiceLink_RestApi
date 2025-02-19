package org.group.smart_city.Service.Implementations;

import org.group.smart_city.Dto.ReclamationDto;
import org.group.smart_city.Entities.Citizen;
import org.group.smart_city.Entities.Reclamation;
import org.group.smart_city.Exceptions.AppException;
import org.group.smart_city.Response.ErrorMessages;
import org.group.smart_city.Respository.ReclamationRepository;
import org.group.smart_city.Service.Interfaces.CitizenService;
import org.group.smart_city.Service.Interfaces.ReclamationService;
import org.group.smart_city.Utils.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReclamationImp implements ReclamationService {
    @Autowired
    ReclamationRepository reclamationRepository;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CitizenService citizenService;
    @Override
    public Reclamation create(ReclamationDto reclamationDto,String idCitizen) {
        if (reclamationDto == null) {
            throw new IllegalArgumentException("reclamationDto must not be null");
        }
        Reclamation reclamation = modelMapper.map(reclamationDto, Reclamation.class);
        Citizen citizen = citizenService.GetById(idCitizen);
        reclamation.setCitizen(citizen);
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
        System.out.println("updated");
        return reclamationRepository.save(reclamation);
    }

    @Override
    public void Delete(String id) {
        if(this.getById(id) != null)
            reclamationRepository.deleteById(id);
    }

    @Override
    public List<Reclamation> GetByServiceProviderAccepted(String id) {
        List<Reclamation> reclamations = reclamationRepository.findAllByServiceProviderId(id);
        List<Reclamation> reclamationListAcc = new ArrayList<>();

        for (Reclamation rec : reclamations) {
            if (rec.getStatus() != -1) {
                reclamationListAcc.add(rec);
            }
        }

        return reclamationListAcc;
    }
    @Override
    public List<Reclamation> GetByServiceProviderIgnored(String id) {
        List<Reclamation> reclamations = reclamationRepository.findAllByServiceProviderId(id);
        List<Reclamation> reclamationListAcc = new ArrayList<>();

        for (Reclamation rec : reclamations) {
            if (rec.getStatus() == -1) {
                reclamationListAcc.add(rec);
            }
        }

        return reclamationListAcc;
    }
    @Override
    public List<Reclamation> GetAllByCitizen(String id)
    {
        return reclamationRepository.findAllByCitizenId(id);
    }

}
