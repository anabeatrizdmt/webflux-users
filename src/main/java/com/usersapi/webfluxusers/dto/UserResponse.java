package com.usersapi.webfluxusers.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserResponse (
        String id,
        String document,
        String email,
        String password,
        LocalDateTime createdAt
) {
}
