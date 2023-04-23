package com.usersapi.webfluxusers.controller.handler;

import com.usersapi.webfluxusers.dto.UserRequest;
import com.usersapi.webfluxusers.dto.UserResponse;
import com.usersapi.webfluxusers.model.User;
import com.usersapi.webfluxusers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersHandler {
    private final UserService userService;

    public Mono<ServerResponse> save(ServerRequest request) {

        return request.bodyToMono(UserRequest.class)
                .flatMap(userService::save)
                .flatMap(response -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(response)));

    }

    public Mono<ServerResponse> getAll(ServerRequest request) {
        Flux<UserResponse> userResponses = userService
                .getAll()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .document(user.getDocument())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .createdAt(user.getCreatedAt())
                        .status(user.getStatus())
                        .build());

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(userResponses, UserResponse.class));
    }

    public Mono<ServerResponse> findById(ServerRequest request) {

        String id = request.pathVariable("id");
        Mono<UserResponse> responseMono = userService.findById(id)
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getDocument(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getCreatedAt(),
                        user.getStatus()
                ));

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters
                        .fromPublisher(responseMono, UserResponse.class));

    }

    public Mono<ServerResponse> getStatusById(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<User.Status> statusMono = userService.getStatusById(id);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(statusMono, User.Status.class));
    }
}
