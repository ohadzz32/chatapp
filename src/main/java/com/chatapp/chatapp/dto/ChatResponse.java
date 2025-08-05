package com.chatapp.chatapp.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ChatResponse {
    private String id;
    private String name;
    private boolean isGroup;
    private String createdBy;
    private List<String> participantIds;
    private LocalDateTime createdAt;
} 