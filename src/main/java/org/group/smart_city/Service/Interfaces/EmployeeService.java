package org.group.smart_city.Service.Interfaces;

import org.group.smart_city.Dto.EmployeeDto;
import org.group.smart_city.Entities.Employee;

public interface EmployeeService {
    Employee create(EmployeeDto employeeDto);
    Employee getById(String id);
    Employee Update(String id, EmployeeDto employeeDto);
    void Delete(String id);
}
