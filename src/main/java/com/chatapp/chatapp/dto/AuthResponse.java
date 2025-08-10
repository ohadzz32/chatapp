package com.chatapp.chatapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    //המטרה של התיקייה הזאתי היא לשמש כמודל שמחזיר את פרטי המשתמש לאחר התחברות או הרשמה כולל token
    //התהליך הזה משלב בתוכו את פרטי המשתמש ולכן הוא מכיל גם כן את UserResponse

    private String token;

    private UserResponse user;

    private String message;
}
