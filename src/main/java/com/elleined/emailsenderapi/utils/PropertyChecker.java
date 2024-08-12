package com.elleined.emailsenderapi.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class PropertyChecker {

    @Autowired
    private Environment env;

    @PostConstruct
    public void checkProperties() {
        System.out.println("MAIL_USERNAME: " + env.getProperty("MAIL_USERNAME"));
        System.out.println("MAIL_PASSWORD: " + env.getProperty("MAIL_PASSWORD"));
        System.out.println("PORT: " + env.getProperty("PORT"));
    }
}
