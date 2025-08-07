package com.chatapp.chatapp;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Chatapp {
	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure()
				.directory("./")
				.ignoreIfMissing()
				.load();

		System.setProperty("MONGO_USERNAME", dotenv.get("MONGO_USERNAME"));
		System.setProperty("MONGO_PASSWORD", dotenv.get("MONGO_PASSWORD"));
		System.setProperty("MONGO_DB", dotenv.get("MONGO_DB"));

		SpringApplication.run(Chatapp.class, args);
	}
}


