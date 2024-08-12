package com.elleined.emailsenderapi.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MessageRequest {

    @Email(message = "Please provide a proper email!")
    @NotBlank(message = "Receiver email cannot be blank, null, or empty!")
    private String receiver;

    @NotBlank(message = "Email subject cannot be blank, null, or empty!")
    private String subject;

    @NotBlank(message = "Email Message cannot be blank, null, or empty!")
    private String messageBody;

}
