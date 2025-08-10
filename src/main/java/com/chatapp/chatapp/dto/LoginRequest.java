package com.chatapp.chatapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    //מודל זה מראה את הפרטים הנדרשים שלמשתמש נכנס לאתר לאחר שהוא כבר רשום

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
}



