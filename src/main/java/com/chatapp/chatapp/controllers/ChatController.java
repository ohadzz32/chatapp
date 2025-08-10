package com.chatapp.chatapp.controllers;

import com.chatapp.chatapp.dto.ChatResponse;
import com.chatapp.chatapp.models.User;
import com.chatapp.chatapp.services.ChatService;
import com.chatapp.chatapp.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
@SuppressWarnings("unused")
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;  // הוספנו את השירות של המשתמשים

    @GetMapping
    public ResponseEntity<List<ChatResponse>> getUserChats(Authentication authentication) {
        String username = authentication.getName();
        log.info("Getting chats for user: {}", username);

        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<ChatResponse> chats = chatService.getUserChats(user.getId());
        return ResponseEntity.ok(chats);
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<ChatResponse> getChatById(@PathVariable String chatId) {
        log.info("Getting chat by ID: {}", chatId);
        ChatResponse chat = chatService.getChatById(chatId);
        return ResponseEntity.ok(chat);
    }

    @PostMapping("/private")
    public ResponseEntity<ChatResponse> createPrivateChat(
            @RequestParam String user2Id,
            Authentication authentication) {
        String username = authentication.getName();
        log.info("Creating private chat between users: {} and {}", username, user2Id);

        User user1 = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ChatResponse chat = chatService.createPrivateChat(user1.getId(), user2Id);
        return ResponseEntity.ok(chat);
    }

    @PostMapping("/group")
    public ResponseEntity<ChatResponse> createGroupChat(
            @RequestParam String name,
            @RequestParam List<String> participantIds,
            Authentication authentication) {
        String username = authentication.getName();
        log.info("Creating group chat: {} by user: {} with participants: {}", name, username, participantIds);

        User createdBy = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ChatResponse chat = chatService.createGroupChat(name, createdBy.getId(), participantIds);
        return ResponseEntity.ok(chat);
    }
}

