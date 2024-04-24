package org.group.smart_city.Entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Citizen")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder


public class Citizen  {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Date dateOfBirth;
    private String address;
    private String phoneNumber;
    private String city;
}
