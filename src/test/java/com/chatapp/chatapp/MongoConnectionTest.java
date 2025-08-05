package com.chatapp.chatapp;

import com.mongodb.client.MongoClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class MongoConnectionTest {

    @Autowired
    private MongoClient mongoClient;

    @Test
    public void testMongoConnection() {
        assertDoesNotThrow(() -> {
            mongoClient.listDatabaseNames().first();
        });
    }
}
