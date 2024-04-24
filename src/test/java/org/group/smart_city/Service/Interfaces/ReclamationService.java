package org.group.smart_city.Service.Interfaces;

import org.group.smart_city.Dto.EmployeeDto;
import org.group.smart_city.Dto.ReclamationDto;
import org.group.smart_city.Entities.Employee;
import org.group.smart_city.Entities.Reclamation;

import java.util.List;

public interface ReclamationService {
    Reclamation create(ReclamationDto reclamationDto);
    Reclamation getById(String id);
    Reclamation Update(String id, ReclamationDto reclamationDto);
    void Delete(String id);
}
