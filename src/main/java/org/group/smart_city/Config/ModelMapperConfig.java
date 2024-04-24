package org.group.smart_city.Config;
import org.group.smart_city.Dto.EmployeeDto;
import org.group.smart_city.Entities.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Add mappings for User and UserDTO
        modelMapper.createTypeMap(Employee.class, EmployeeDto.class)
                .addMapping(Employee::getEmail, EmployeeDto::setEmail)
                .addMapping(Employee::getFirstName, EmployeeDto::setFirstName)
                .addMapping(Employee::getPhoneNumber, EmployeeDto::setPhoneNumber);

        return modelMapper;
    }
}
