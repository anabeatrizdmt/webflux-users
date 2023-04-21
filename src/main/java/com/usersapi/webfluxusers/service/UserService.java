package com.usersapi.webfluxusers.service;

import com.usersapi.webfluxusers.dto.UserRequest;
import com.usersapi.webfluxusers.dto.UserResponse;
import com.usersapi.webfluxusers.model.User;
import com.usersapi.webfluxusers.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public Mono<UserResponse> save(UserRequest userRequest) {

        String uid = UUID.randomUUID().toString();

        var userEntity = new User(
                uid,
                userRequest.document(),
                userRequest.email(),
                userRequest.password(),
                LocalDateTime.now()
        );

        repository.save(userEntity);

        return Mono.defer(() -> Mono.just(
                new UserResponse(
                        uid,
                        userRequest.document(),
                        userRequest.email(),
                        userRequest.password(),
                        LocalDateTime.now()
                )
        ));
    }

    public Mono<UserResponse> findById(String id) {
        return Mono.defer(() -> Mono.justOrEmpty(repository.findById(id)))
                .subscribeOn(Schedulers.boundedElastic())
                .map(entity -> {
                    return new UserResponse(
                            entity.id(),
                            entity.document(),
                            entity.email(),
                            entity.password(),
                            entity.createdAt()
                    );
                });
    }
}
