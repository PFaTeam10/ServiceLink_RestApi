package org.group.smart_city.Entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ServiceType")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ServiceType {
    @Id
    private String id;
    private String name;
    private String description;
}
