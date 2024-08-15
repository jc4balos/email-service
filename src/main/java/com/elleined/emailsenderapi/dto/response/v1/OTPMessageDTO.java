package com.elleined.emailsenderapi.dto.response.v1;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OTPMessageDTO {
    private String receiver;

    private String subject;

    private int otp;

    private LocalDateTime expiration;

}
