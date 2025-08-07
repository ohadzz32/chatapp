package com.chatapp.chatapp.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class MessageResponse {

    private String id;

    private String chatId;

    private String text;

    private String senderId;

    private String senderName;

    private LocalDateTime createdAt;
} 