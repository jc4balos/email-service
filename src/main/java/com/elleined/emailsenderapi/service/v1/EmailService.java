package com.elleined.emailsenderapi.service.v1;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elleined.emailsenderapi.dto.request.v1.MessageRequest;
import com.elleined.emailsenderapi.dto.request.v1.OtpMessageRequest;
import com.elleined.emailsenderapi.dto.response.v1.OtpMessageResponse;

import jakarta.mail.MessagingException;

@Service
public interface EmailService {
    void send(MessageRequest messageRequest) throws MessagingException;

    OtpMessageResponse send(OtpMessageRequest otpMessageRequest) throws MessagingException;

    void send(MessageRequest messageRequest, MultipartFile attachment) throws MessagingException, IOException;

}
