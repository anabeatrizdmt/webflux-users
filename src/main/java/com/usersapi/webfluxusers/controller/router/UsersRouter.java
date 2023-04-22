package com.usersapi.webfluxusers.controller.router;

import com.usersapi.webfluxusers.controller.handler.UsersHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
@RequiredArgsConstructor
public class UsersRouter {
    private final UsersHandler usersHandler;

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions
                .route(POST("/users"), usersHandler::save)
                .andRoute(GET("/users"), usersHandler::getAll)
                .andRoute(GET("/users/{id}"), usersHandler::findById);
    }
}
