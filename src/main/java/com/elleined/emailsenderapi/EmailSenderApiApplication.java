package com.elleined.emailsenderapi;

import java.security.SecureRandom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmailSenderApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailSenderApiApplication.class, args);
	}

	@Bean
	public SecureRandom secureRandom() {
		return new SecureRandom();
	}

}
