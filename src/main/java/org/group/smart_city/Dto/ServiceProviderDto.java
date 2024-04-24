package org.group.smart_city.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
}
