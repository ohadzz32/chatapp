package com.chatapp.chatapp.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "chat")


public class Chat {
    @Id
    private String id;

    private List<String> participantIds;
    private boolean isGroup;
    private String createdBy;

    @Builder.Default
    private Date createdAt = new Date();

    private String name;
}
