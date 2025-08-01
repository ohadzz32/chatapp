package com.chatapp.chatapp.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data // יוצר אוטומטית גטרים, סטרים, toString, equals, hashCode
@Builder // מאפשר שימוש ב-Builder Pattern ליצירת אובייקטים
@NoArgsConstructor // קונסטרקטור ריק - חשוב ל-Spring/JPA ליצירת האובייקט דרך רפלקשן
@AllArgsConstructor // קונסטרקטור עם כל הפרמטרים - נוח ליצירת אובייקטים חדשים בקלות
@Document(collection = "user")

public class User {

    @Id
    private String id; // מזהה ייחודי במסד הנתונים

    private String username;

    @Indexed(unique = true)
    private String email;

    private String password;

    @Builder.Default
    private Date createdAt = new Date(); // ברירת מחדל: תאריך יצירה אוטומטי בזמן יצירת האובייקט
}

