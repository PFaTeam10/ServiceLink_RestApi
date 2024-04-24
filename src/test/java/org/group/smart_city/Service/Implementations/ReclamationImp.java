package org.group.smart_city.Service.Implementations;

import org.group.smart_city.Dto.EmployeeDto;
import org.group.smart_city.Dto.ReclamationDto;
import org.group.smart_city.Entities.Employee;
import org.group.smart_city.Entities.Reclamation;
import org.group.smart_city.Service.Interfaces.ReclamationService;
import org.springframework.stereotype.Service;

@Service
public class ReclamationImp implements ReclamationService {
    @Override
    public Reclamation create(ReclamationDto reclamationDto) {
        return null;
    }

    @Override
    public Reclamation getById(String id) {
        return null;
    }

    @Override
    public Reclamation Update(String id, ReclamationDto reclamationDto) {
        return null;
    }

    @Override
    public void Delete(String id) {

    }
}
