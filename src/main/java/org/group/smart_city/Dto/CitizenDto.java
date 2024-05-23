package org.group.smart_city.Dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CitizenDto {

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
