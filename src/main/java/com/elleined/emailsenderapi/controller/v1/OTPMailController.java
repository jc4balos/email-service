package com.elleined.emailsenderapi.controller.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elleined.emailsenderapi.dto.request.v1.OtpMessageRequest;
import com.elleined.emailsenderapi.exception.ApplicationExceptionHandler;
import com.elleined.emailsenderapi.service.v1.EmailService;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/mail")
@RequiredArgsConstructor
public class OTPMailController {
    private final EmailService emailService;

    @PostMapping("/otp-mail")
    public ResponseEntity<?> send(@Valid @RequestBody OtpMessageRequest otpMessageRequest, BindingResult bindingResult)
            throws MessagingException {

        try {
            if (!bindingResult.hasErrors()) {
                return new ResponseEntity<>(emailService.send(otpMessageRequest),
                        HttpStatus.OK);
            } else {
                return ApplicationExceptionHandler.handleBadRequest(bindingResult);
            }
        } catch (Exception e) {
            return ApplicationExceptionHandler.handleCustomException(e);
        }

    }
}
