package com.usersapi.webfluxusers.service;

import com.usersapi.webfluxusers.dto.UserRequest;
import com.usersapi.webfluxusers.dto.UserResponse;
import com.usersapi.webfluxusers.model.User;
import com.usersapi.webfluxusers.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public Mono<User> save(UserRequest request) {

        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .document(request.getDocument())
                .email(request.getEmail())
                .password(request.getPassword())
                .createdAt(LocalDateTime.now())
                .build();
        return repository.save(user);
    }

    public Flux<User> getAll() {
        return repository.findAll();
    }

    public Mono<User> findById(String id) {
        return repository.findById(id);
    }
}
