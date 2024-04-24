package org.group.smart_city.Service.Implementations;

import org.group.smart_city.Dto.EmployeeDto;
import org.group.smart_city.Entities.Employee;
import org.group.smart_city.Exceptions.AppException;
import org.group.smart_city.Response.ErrorMessages;
import org.group.smart_city.Respository.EmployeeRepository;
import org.group.smart_city.Service.Interfaces.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeImp implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    private List<Employee> employees = new ArrayList<>();
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public Employee create(EmployeeDto employeeDto) {
        if (employeeDto == null) {
            throw new IllegalArgumentException("employeeDto must not be null");
        }
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        if (employee == null) {
            throw new AppException("Mapping from employeeDto to employee failed");
        }

        return employeeRepository.save(employee);
    }

    @Override
    public Employee getById(String id) {
        return employeeRepository.findById(id).orElseThrow(()
                ->  new AppException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
    }

    @Override
    public Employee Update(String id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));

        // Update project object with fields from projectDto
        modelMapper.map(employeeDto, employee);
        return employee;
    }

    @Override
    public void Delete(String id) {
        if(this.getById(id) != null)
            employeeRepository.deleteById(id);
    }
}
