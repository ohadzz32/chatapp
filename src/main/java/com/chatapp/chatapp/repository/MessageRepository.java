package com.chatapp.chatapp.repository;

import com.chatapp.chatapp.models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends MongoRepository <Message, String>
{
    List<Message> findByChatIdOrderByCreatedAtAsc(String chatId);

    Optional<Message> findById(String id);
}
