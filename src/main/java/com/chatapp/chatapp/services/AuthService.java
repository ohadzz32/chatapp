package com.chatapp.chatapp.services;

import com.chatapp.chatapp.dto.AuthResponse;
import com.chatapp.chatapp.dto.LoginRequest;
import com.chatapp.chatapp.dto.RegisterRequest;
import com.chatapp.chatapp.dto.UserResponse;
import com.chatapp.chatapp.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    public AuthResponse register(RegisterRequest request) {
        UserResponse userResponse = userService.registerUser(request);
        String token = jwtService.generateToken(userResponse.getEmail());
        
        return AuthResponse.builder()
                .token(token)
                .user(userResponse)
                .message("User registered successfully")
                .build();
    }
    
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        
        User user = userService.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        UserResponse userResponse = userService.getUserById(user.getId());
        String token = jwtService.generateToken(userResponse.getEmail());
        
        userService.markUserAsOnline(user.getId());
        
        return AuthResponse.builder()
                .token(token)
                .user(userResponse)
                .message("Login successful")
                .build();
    }
} 