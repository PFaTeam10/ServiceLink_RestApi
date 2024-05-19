package org.group.smart_city.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.group.smart_city.Entities.ServiceProvider;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GroupDto {

    private ServiceProvider serviceProvider;
    private String name;
    private String description;

}
