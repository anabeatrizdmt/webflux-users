package com.usersapi.webfluxusers.model;

import lombok.Builder;
import lombok.Data;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Builder
@With
@Document(value = "users")
public class User {
    @Id
    private String id;
    private String document;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private Status status;


    public enum Status {
        ACTIVE, BLOCKED
    }
}
