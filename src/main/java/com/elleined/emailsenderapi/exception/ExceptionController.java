package com.elleined.emailsenderapi.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.elleined.emailsenderapi.dto.response.v1.APIResponse;

import jakarta.mail.MessagingException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<APIResponse> handleMessagingException(MessagingException ex) {
        var responseMessage = new APIResponse(HttpStatus.SERVICE_UNAVAILABLE,
                "An error occured on email service " + ex.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<APIResponse> handleIOException(IOException ex) {
        var responseMessage = new APIResponse(HttpStatus.NOT_ACCEPTABLE,
                "Cannot send email with attachement! " + ex.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_ACCEPTABLE);
    }
}
