package org.group.smart_city.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.group.smart_city.Entities.Employee;
import org.group.smart_city.Entities.ServiceType;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProviderDto {


    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String password;
    private String image;
    private String description;
}
