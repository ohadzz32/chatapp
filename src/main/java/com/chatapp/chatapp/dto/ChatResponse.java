package com.chatapp.chatapp.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ChatResponse {
// המטרה של התיקייה הזאתי היא לשמש כמודל שמחזיר את פרטי הצ'אט ואת המשתתפים בו

    private String id;

    private String name;

    private boolean isGroup;

    private String createdBy;

    private List<String> participantIds;

    private LocalDateTime createdAt;
}

