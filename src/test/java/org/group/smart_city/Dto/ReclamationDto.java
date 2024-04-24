package org.group.smart_city.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReclamationDto {
    private String id;
    private String titre;
    private String description;
    private String statut;
    private String localisation;
    private int priorite;
    private List<String> media;
}
