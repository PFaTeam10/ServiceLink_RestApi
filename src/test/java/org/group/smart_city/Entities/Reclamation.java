package org.group.smart_city.Entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Reclamation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Reclamation {
    @Id
    private String id;
    private String titre;
    private String description;
    private String statut;
    private String localisation;
    private int priorite;
    private List<String> media;
    @DBRef
    private ServiceProvider serviceProvider;
}
