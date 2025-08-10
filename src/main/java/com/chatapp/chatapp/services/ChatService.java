package com.chatapp.chatapp.services;

import com.chatapp.chatapp.dto.ChatResponse;
import com.chatapp.chatapp.models.Chat;
import com.chatapp.chatapp.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {
    
    private final ChatRepository chatRepository;
    
    public ChatResponse createPrivateChat(String user1Id, String user2Id) {
        List<Chat> existingChats = chatRepository.findByParticipantIdsContaining(user1Id);
        for (Chat chat : existingChats) {
            if (!chat.isGroup() && chat.getParticipantIds().contains(user2Id)) {
                return mapToChatResponse(chat);
            }
        }
        
        Chat chat = Chat.builder()
                .participantIds(List.of(user1Id, user2Id))
                .isGroup(false)
                .createdBy(user1Id)
                .name("Private Chat")
                .build();
        
        Chat savedChat = chatRepository.save(chat);
        return mapToChatResponse(savedChat);
    }
    
    public ChatResponse createGroupChat(String name, String createdBy, List<String> participantIds) {
        Chat chat = Chat.builder()
                .name(name)
                .participantIds(participantIds)
                .isGroup(true)
                .createdBy(createdBy)
                .build();
        
        Chat savedChat = chatRepository.save(chat);
        return mapToChatResponse(savedChat);
    }
    
    public List<ChatResponse> getUserChats(String userId) {
        return chatRepository.findByParticipantIdsContaining(userId).stream()
                .map(this::mapToChatResponse)
                .collect(Collectors.toList());
    }
    
    public ChatResponse getChatById(String chatId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Chat not found"));
        return mapToChatResponse(chat);
    }
    
    private ChatResponse mapToChatResponse(Chat chat) {
        return ChatResponse.builder()
                .id(chat.getId())
                .name(chat.getName())
                .isGroup(chat.isGroup())
                .createdBy(chat.getCreatedBy())
                .participantIds(chat.getParticipantIds())
                .createdAt(chat.getCreatedAt().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime())
                .build();
        //הפונקציאה הזאתי מחזירה אובייקט בצורה של dto שקבענו מראש במקרה הזה ChatResponse
    }
} 