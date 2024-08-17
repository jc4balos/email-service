package com.elleined.emailsenderapi.dto.request.v1;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OtpMessageRequest {

    @Email(message = "Please provide a proper email!")
    @NotBlank(message = "Receiver email cannot be blank, null, or empty!")
    private String receiver;

    @NotBlank(message = "Please insert the reason for OTP Verification")
    private String service;

    @NotBlank(message = "Application name is required")
    private String appName;

    @Positive(message = "Please provide the OTP timeout in minutes")
    private int timeout;

}
