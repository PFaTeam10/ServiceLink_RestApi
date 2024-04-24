package org.group.smart_city.Entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "MessageDto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Message {
    @Id
    private String id;
    @DBRef
    private Citizen citizen;
    @DBRef
    private Group group;
    private String message;
    private Date timestamp;
    private String attachmentUrl;
}
