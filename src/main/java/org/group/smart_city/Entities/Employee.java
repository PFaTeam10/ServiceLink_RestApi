package org.group.smart_city.Entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Employee {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    @DBRef
    private ServiceProvider serviceProvider;
}
