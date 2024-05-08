package org.group.smart_city.Entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
@Document(collection = "Group")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Group {
    @Id
    private String id;
    private ServiceProvider serviceProvider;
    private String name;
    private String description;

}
