package com.chatapp.chatapp.controllers;

import com.chatapp.chatapp.dto.MessageResponse;
import com.chatapp.chatapp.services.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
@SuppressWarnings("unused")
public class MessageController {
    
    private final MessageService messageService;
    
    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<MessageResponse>> getChatMessages(@PathVariable String chatId) {
        log.info("Getting messages for chat: {}", chatId);
        List<MessageResponse> messages = messageService.getChatMessages(chatId);
        return ResponseEntity.ok(messages);
    }
    
    @PostMapping("/chat/{chatId}")
    public ResponseEntity<MessageResponse> sendMessage(
            @PathVariable String chatId,
            @RequestParam String text,
            Authentication authentication) {
        String senderId = authentication.getName();
        log.info("Sending message to chat: {} by user: {}", chatId, senderId);
        MessageResponse message = messageService.sendMessage(chatId, senderId, text);
        return ResponseEntity.ok(message);
    }
    
    @GetMapping("/{messageId}")
    public ResponseEntity<MessageResponse> getMessageById(@PathVariable String messageId) {
        log.info("Getting message by ID: {}", messageId);
        MessageResponse message = messageService.getMessageById(messageId);
        return ResponseEntity.ok(message);
    }
    
    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable String messageId, Authentication authentication) {
        String username = authentication.getName();
        log.info("Deleting message: {} by user: {}", messageId, username);
        messageService.deleteMessage(messageId, username);
        return ResponseEntity.ok().build();
    }
} 