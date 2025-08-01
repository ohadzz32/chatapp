package com.chatapp.chatapp.repository;

import com.chatapp.chatapp.models.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface ChatRepository extends MongoRepository<Chat, String>
{
    List<Chat> findByParticipantIdsContaining(String userId);

    Optional<Chat> findById(String chatId);


    List<Chat> findByIsGroup(boolean isGroup);

    Optional<Chat> findByNameOfTheGroup(String name);
}
