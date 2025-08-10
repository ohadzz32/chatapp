package com.chatapp.chatapp;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Chatapp {
	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure() //יצירת אובייקט Dotenv שמטרו לטעון את המשתנים החיצונים
				.directory("./") // מיקום הקובץ בגלל שהוא נמצא במיקום החיצוני
				.ignoreIfMissing() // להמשיך את הריצה ולא לעצור את התוכנית במקרה שלא נמצא
				.load(); //לטעון את המשתנים

		System.setProperty("MONGO_USERNAME", dotenv.get("MONGO_USERNAME"));
		System.setProperty("MONGO_PASSWORD", dotenv.get("MONGO_PASSWORD"));
		System.setProperty("MONGO_DB", dotenv.get("MONGO_DB"));
		System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));


		SpringApplication.run(Chatapp.class, args);
	}
}


