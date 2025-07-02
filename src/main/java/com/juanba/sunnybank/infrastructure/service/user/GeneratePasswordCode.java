package com.juanba.sunnybank.infrastructure.service.user;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class GeneratePasswordCode {

    public String generateCode() {
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int acciCode = ThreadLocalRandom.current().nextInt(65, 90);
            code.append((char) acciCode);
        }

        return code.toString();
    }
}
