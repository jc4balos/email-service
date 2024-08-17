package com.elleined.emailsenderapi.dto.response.v1;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OtpMessageResponse {

    private int otp;

    private LocalDateTime expiration;

}
