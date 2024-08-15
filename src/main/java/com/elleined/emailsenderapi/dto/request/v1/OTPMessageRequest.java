package com.elleined.emailsenderapi.request.otp;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OTPMessageRequest {

    @Email(message = "Please provide a proper email!")
    @NotBlank(message = "Receiver email cannot be blank, null, or empty!")
    private String receiver;

    @NotBlank(message = "Email subject cannot be blank, null, or empty!")
    private String subject;

    @NotBlank(message = "Application name is required")
    private String appName;

    @NotBlank(message = "Body must not be empty")
    private String body;

    @Positive(message = "Please provide seconds to be added in expiration time from date time now")
    private int plusExpirationSeconds;

}
