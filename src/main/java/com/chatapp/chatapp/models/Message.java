package com.chatapp.chatapp.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "message")


public class Message {
    @Id
    private String id;
    private String chatId;
    private String text;
    private String senderId;

    @Builder.Default
    private Date createdAt = new Date();
}
