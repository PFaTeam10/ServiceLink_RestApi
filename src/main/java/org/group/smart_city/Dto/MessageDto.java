package org.group.smart_city.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.group.smart_city.Entities.Citizen;
import org.group.smart_city.Entities.Group;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageDto {

    private Citizen citizen;
    private Group group;
    private String message;
    private Date timestamp;

}
