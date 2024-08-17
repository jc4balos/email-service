package com.elleined.emailsenderapi.service.v1;

import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elleined.emailsenderapi.dto.request.v1.MessageRequest;
import com.elleined.emailsenderapi.dto.request.v1.OtpMessageRequest;
import com.elleined.emailsenderapi.dto.response.v1.OtpMessageResponse;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Autowired
    private final JavaMailSender javaMailSender;

    private final SecureRandom secureRandom;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public void send(MessageRequest messageRequest) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

        messageHelper.setFrom(sender);
        messageHelper.setTo(messageRequest.getReceiver());
        messageHelper.setSubject(messageRequest.getSubject());
        messageHelper.setText(messageRequest.getMessageBody());

        javaMailSender.send(mimeMessage);
        log.debug("Sending simple mail success!");
    }

    @Override
    @Async
    public OtpMessageResponse send(OtpMessageRequest otpMessageRequest) throws MessagingException {

        LocalDateTime expiration = LocalDateTime.now().plusMinutes(otpMessageRequest.getTimeout());
        int otp = secureRandom.nextInt(100_000, 999_999);

        String message = String.format("""
                    <html>
                    <body>
                        <p>Please enter the following verification code for %s:</p>

                        <p><b>Verification Code: %d</b></p>

                        <p>Expiration: The verification code expires within %d minutes.</p>

                        <p>If you did not request this code, please ignore this message.</p>

                    </body>
                    </html>
                """, otpMessageRequest.getService(), otp, otpMessageRequest.getTimeout());

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

        messageHelper.setFrom(sender);
        messageHelper.setTo(otpMessageRequest.getReceiver());
        messageHelper.setSubject("[DO NOT SHARE]: OTP Verification - " + otpMessageRequest.getService() + " ("
                + otpMessageRequest.getAppName() + ")");
        messageHelper.setText(message, true);

        javaMailSender.send(mimeMessage);

        OtpMessageResponse response = OtpMessageResponse.builder().expiration(expiration).otp(otp).build();
        log.debug("Sending otp mail success!");
        return response;
    }

    @Override
    public void send(MessageRequest messageRequest, MultipartFile attachment) throws MessagingException, IOException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

        messageHelper.setFrom(sender);
        messageHelper.setTo(messageRequest.getReceiver());
        messageHelper.setSubject(messageRequest.getSubject());
        messageHelper.setText(messageRequest.getMessageBody());

        messageHelper.addAttachment(Objects.requireNonNull(attachment.getOriginalFilename()),
                new ByteArrayResource(attachment.getBytes()));

        javaMailSender.send(mimeMessage);
        log.debug("Email with attachment sent successfully!");
    }
}
