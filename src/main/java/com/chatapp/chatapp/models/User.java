package com.chatapp.chatapp.models;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Encrypted;

import java.time.LocalDateTime;
import java.util.Date;

@Data // יוצר אוטומטית גטרים, סטרים, toString, equals, hashCode
@Builder // מאפשר שימוש ב-Builder Pattern ליצירת אובייקטים
@NoArgsConstructor // קונסטרקטור ריק - חשוב ל-Spring/JPA ליצירת האובייקט דרך רפלקשן
@AllArgsConstructor // קונסטרקטור עם כל הפרמטרים - נוח ליצירת אובייקטים חדשים בקלות
@Document(collection = "user")

public class User {

    @Id
    private String id;

    @NotBlank
    @Indexed(unique = true)
    private String username;

    @Email
    @Indexed(unique = true)
    private String email;

    @Size(min = 8)
    @NotBlank
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 chars and include uppercase, lowercase, number and special char"
    ) //הסיסמה צריכה להיות לפחות עם אות גדולה וקטנה אחת בנוסף סימן מיוחד ושהאורך שלה יהיה שמונה תווים
    private String password;

    @Builder.Default
    private Date createdAt = new Date(); // ברירת מחדל: תאריך יצירה אוטומטי בזמן יצירת האובייקט

    private String firstName;

    private String lastName;

    @Builder.Default
    private boolean isOnline = false;

    @Builder.Default
    private LocalDateTime lastSeen = LocalDateTime.now();


    public void markAsOnline() {
        this.isOnline = true;
        this.lastSeen = LocalDateTime.now();
    }


    public void markAsOffline() {
        this.isOnline = false;
        this.lastSeen = LocalDateTime.now();
    }

    public String getFullName()
    {
        return getFirstName() + " " + getLastName();
    }
}
