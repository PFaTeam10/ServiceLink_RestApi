package org.group.smart_city.Entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "ServiceProvider")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ServiceProvider {
    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    @DBRef
    public ServiceType serviceType;
    @DBRef
    List<Employee> employeeList;
}
