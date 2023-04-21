package com.usersapi.webfluxusers.dto;

import java.time.LocalDateTime;

public record UserResponse (
        String id,
        String document,
        String email,
        String password,
        LocalDateTime createdAt
) {
}
