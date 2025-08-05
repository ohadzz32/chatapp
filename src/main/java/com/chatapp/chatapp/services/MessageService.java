package com.chatapp.chatapp.services;

import com.chatapp.chatapp.dto.MessageResponse;
import com.chatapp.chatapp.models.Message;
import com.chatapp.chatapp.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("unused")
public class MessageService {
    
    private final MessageRepository messageRepository;
    private final UserService userService;
    
    public MessageResponse sendMessage(String chatId, String senderId, String text) {
        Message message = Message.builder()
                .chatId(chatId)
                .senderId(senderId)
                .text(text)
                .build();
        
        Message savedMessage = messageRepository.save(message);
        return mapToMessageResponse(savedMessage);
    }
    
    public List<MessageResponse> getChatMessages(String chatId) {
        return messageRepository.findByChatIdOrderByCreatedAtAsc(chatId).stream()
                .map(this::mapToMessageResponse)
                .collect(Collectors.toList());
    }
    
    public MessageResponse getMessageById(String messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found"));
        return mapToMessageResponse(message);
    }
    
    public void deleteMessage(String messageId, String username) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found"));
        
        // בדיקה אם המשתמש הוא השולח של ההודעה
        if (!message.getSenderId().equals(username)) {
            throw new RuntimeException("You can only delete your own messages");
        }
        
        messageRepository.delete(message);
        log.info("Message deleted: {} by user: {}", messageId, username);
    }
    
    private MessageResponse mapToMessageResponse(Message message) {
        String senderName = userService.getUserById(message.getSenderId()).getFullName();
        
        return MessageResponse.builder()
                .id(message.getId())
                .chatId(message.getChatId())
                .text(message.getText())
                .senderId(message.getSenderId())
                .senderName(senderName)
                .createdAt(message.getCreatedAt().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime())
                .build();
    }
} 