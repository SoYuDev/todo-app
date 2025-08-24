package com.luis.todoapp.shared.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

    // PasswordEncoder is an interface used to encode passwords when saving them.
    // A typical use for this bean is:
    // String encoded = passwordEncoder.encode("mypassword");
    // boolean match = passwordEncoder.matches("mypassword", encoded) //true
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
