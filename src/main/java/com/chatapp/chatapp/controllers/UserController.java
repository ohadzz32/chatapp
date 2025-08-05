package com.chatapp.chatapp.controllers;

import com.chatapp.chatapp.dto.UserResponse;
import com.chatapp.chatapp.models.User;
import com.chatapp.chatapp.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
@SuppressWarnings("unused")
public class UserController {
    
    private final UserService userService;
    
    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getCurrentUser(Authentication authentication) {
        String username = authentication.getName();
        log.info("Getting profile for user: {}", username);
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserResponse userResponse = userService.getUserById(user.getId());
        return ResponseEntity.ok(userResponse);
    }
    
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        log.info("Getting all users");
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        log.info("Getting user by ID: {}", id);
        UserResponse user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    
    @PutMapping("/online")
    public ResponseEntity<Void> markUserAsOnline(Authentication authentication) {
        String username = authentication.getName();
        log.info("Marking user as online: {}", username);
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userService.markUserAsOnline(user.getId());
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/offline")
    public ResponseEntity<Void> markUserAsOffline(Authentication authentication) {
        String username = authentication.getName();
        log.info("Marking user as offline: {}", username);
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userService.markUserAsOffline(user.getId());
        return ResponseEntity.ok().build();
    }
} 