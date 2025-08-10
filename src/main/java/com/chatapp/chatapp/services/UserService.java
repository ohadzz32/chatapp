
package com.chatapp.chatapp.services;

import com.chatapp.chatapp.dto.RegisterRequest;
import com.chatapp.chatapp.dto.UserResponse;
import com.chatapp.chatapp.exceptions.DuplicateResourceException;
import com.chatapp.chatapp.models.User;
import com.chatapp.chatapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // מצפין סיסמאות

    public UserResponse registerUser(RegisterRequest request) {
        log.info("Registering new user: {}", request.getUsername());

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            log.warn("Email already exists: {}", request.getEmail());
            throw new DuplicateResourceException("Email already exists");
        }
        
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            log.warn("Username already exists: {}", request.getUsername());
            throw new DuplicateResourceException("Username already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();

        User savedUser = userRepository.save(user);
        log.info("User registered successfully: {}", savedUser.getId());
        return mapToUserResponse(savedUser);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private User getUserByIdOrThrow(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserResponse getUserById(String userId) {
        User user = getUserByIdOrThrow(userId);
        return mapToUserResponse(user);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    public void markUserAsOnline(String userId) {
        User user = getUserByIdOrThrow(userId);
        user.markAsOnline();
        userRepository.save(user);
    }
    public void markUserAsOffline(String userId) {
        User user = getUserByIdOrThrow(userId);
        user.markAsOffline();
        userRepository.save(user);
    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .fullName(user.getFullName())
                .isOnline(user.isOnline())
                .lastSeen(user.getLastSeen())
                .createdAt(user.getCreatedAt().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime())
                .build();
    }
}
