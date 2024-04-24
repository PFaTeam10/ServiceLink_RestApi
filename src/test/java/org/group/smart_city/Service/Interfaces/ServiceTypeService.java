package org.group.smart_city.Service.Interfaces;

import org.group.smart_city.Dto.EmployeeDto;
import org.group.smart_city.Dto.ServiceTypeDto;
import org.group.smart_city.Entities.Employee;
import org.group.smart_city.Entities.ServiceType;

public interface ServiceTypeService {
    ServiceType create(ServiceType serviceType);
    ServiceType getById(String id);
    ServiceType Update(String id, ServiceTypeDto serviceTypeDto);
    void Delete(String id);
}
