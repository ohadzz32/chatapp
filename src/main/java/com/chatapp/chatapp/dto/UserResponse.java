package com.chatapp.chatapp.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {

    //המטרה של התיקייה הזאתי היא לשמש כמודל שמחזיר את פרטי המשתמש
    //קורא אחרי התחברות או הרשמה כדי להימנע רגיש ולהציג רק את מה שרלוונטי

    private String id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String fullName;

    private boolean isOnline;

    private LocalDateTime lastSeen;

    private LocalDateTime createdAt;
}
