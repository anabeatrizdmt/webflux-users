package com.usersapi.webfluxusers.model;

import java.time.LocalDateTime;

public record User (
        String id,
        String document,
        String email,
        String password,
        LocalDateTime createdAt
) {
    public String getId() {
        return id;
    }
}
