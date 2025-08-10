package com.chatapp.chatapp.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class MessageResponse {
    // המטרה של התיקייה הזאתי היא לשמש כמודל שמחזיר את פרטי ההודעה ואת ההודעות שיש בצאט

    private String id;

    private String chatId;

    private String text;

    private String senderId;

    private String senderName;

    private LocalDateTime createdAt;
} 