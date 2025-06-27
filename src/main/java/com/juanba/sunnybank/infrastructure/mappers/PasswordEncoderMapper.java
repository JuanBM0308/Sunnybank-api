package com.juanba.sunnybank.infrastructure.mappers;

import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderMapper {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordEncoderMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Named("encodePassword")
    public String encode(String rawPassword) {
        if (rawPassword == null) {
            return null;
        }
        return passwordEncoder.encode(rawPassword);
    }
}
