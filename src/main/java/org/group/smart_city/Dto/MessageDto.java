package org.group.smart_city.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.group.smart_city.Entities.Citizen;
import org.group.smart_city.Entities.ServiceProvider;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageDto {

    private Citizen citizen;
    private ServiceProvider serviceProvider;
    private String message;
    private Date timestamp;
    private boolean isCitizenSender;

}
