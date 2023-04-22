package com.usersapi.webfluxusers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserRequest {
        @JsonProperty("document")
        private String document;

        @JsonProperty("email")
        private String email;

        @JsonProperty("password")
        private String password;
}