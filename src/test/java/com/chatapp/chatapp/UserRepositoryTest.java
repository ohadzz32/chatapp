package com.chatapp.chatapp;

import com.chatapp.chatapp.models.User;
import com.chatapp.chatapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndFindByEmail() {
        User user = User.builder()
                .username("ohad")
                .email("ohad@example.com")
                .password("1234")
                .build();

        userRepository.save(user);

        Optional<User> found = userRepository.findByEmail("ohad@example.com");

        assertThat(found).isPresent();
        assertThat(found.get().getUsername()).isEqualTo("ohad");
    }

    @Test
    public void testFindByUsername() {
        User user = User.builder()
                .username("ohad")
                .email("ohad2@example.com")
                .password("1234")
                .build();

        userRepository.save(user);

        Optional<User> found = userRepository.findByUsername("ohad");

        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("ohad2@example.com");
    }
}
