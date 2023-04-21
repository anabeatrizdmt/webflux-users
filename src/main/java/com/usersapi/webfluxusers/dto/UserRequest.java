package com.usersapi.webfluxusers.dto;

public record UserRequest (
        String document,
        String email,
        String password
        ) { }
