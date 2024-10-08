package com.elleined.emailsenderapi.controller.v1;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elleined.emailsenderapi.dto.request.v1.MessageRequest;
import com.elleined.emailsenderapi.service.v1.EmailService;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/mail")
public class MailController {

    @Autowired
    private final EmailService emailService;

    @PostMapping("/simple-mail")
    public void send(@Valid @RequestBody MessageRequest messageRequest) throws MessagingException {

        emailService.send(messageRequest);
    }

    @PostMapping("/attachment-mail")
    public void send(@Valid @RequestPart("messageRequest") MessageRequest messageRequest,
            @RequestPart("attachment") MultipartFile attachment) throws MessagingException, IOException {
        emailService.send(messageRequest, attachment);
    }
}
