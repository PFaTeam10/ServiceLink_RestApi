package org.group.smart_city.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.group.smart_city.Entities.ServiceProvider;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReclamationDto {
    private String id;
    private String title;
    private String description;
    private String status;
    private String localization;
    private int priority;
    private List<String> media;
    private ServiceProvider serviceProvider;
}
