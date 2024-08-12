package com.elleined.emailsenderapi.request;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class APIResponse {
    private final HttpStatus status;
    private final String message;
    private final LocalDateTime timeStamp;

    public APIResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }
}
