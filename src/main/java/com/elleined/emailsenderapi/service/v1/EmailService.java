package com.elleined.emailsenderapi.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elleined.emailsenderapi.request.MessageRequest;
import com.elleined.emailsenderapi.request.otp.OTPMessageRequest;

import jakarta.mail.MessagingException;

@Service
public interface EmailService {
    void send(MessageRequest messageRequest) throws MessagingException;

    void send(OTPMessageRequest otpMessageRequest, String message) throws MessagingException;

    void send(MessageRequest messageRequest, MultipartFile attachment) throws MessagingException, IOException;

}
