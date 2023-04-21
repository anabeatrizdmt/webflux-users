package com.usersapi.webfluxusers.controller.handler;

import com.usersapi.webfluxusers.dto.UserRequest;
import com.usersapi.webfluxusers.dto.UserResponse;
import com.usersapi.webfluxusers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

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

    public Mono<ServerResponse> findById(ServerRequest request) {

        String id = request.pathVariable("id");

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters
                        .fromPublisher(userService.findById(id), UserResponse.class));

    }

}
