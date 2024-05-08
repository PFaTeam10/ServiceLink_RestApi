package org.group.smart_city.Config;
import org.group.smart_city.Dto.*;
import org.group.smart_city.Entities.*;
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
        modelMapper.createTypeMap(Message.class, MessageDto.class);
        modelMapper.createTypeMap(Citizen.class, CitizenDto.class);
        modelMapper.createTypeMap(Group.class, GroupDto.class);
        modelMapper.createTypeMap(Reclamation.class, ReclamationDto.class);
        modelMapper.createTypeMap(ServiceProvider.class, ServiceProviderDto.class);

        return modelMapper;
    }
}
