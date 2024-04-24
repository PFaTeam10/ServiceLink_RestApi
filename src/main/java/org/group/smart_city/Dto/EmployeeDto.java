package org.group.smart_city.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto {
    public String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
