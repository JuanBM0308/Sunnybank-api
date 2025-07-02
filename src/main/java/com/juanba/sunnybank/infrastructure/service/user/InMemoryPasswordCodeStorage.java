package com.juanba.sunnybank.infrastructure.service.user;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryPasswordCodeStorage implements PasswordCodeStorage {

    private final Map<Long, String> userCodes = new ConcurrentHashMap<>();


    @Override
    public void storeCodeForUser(Long userId, String code) {
        userCodes.put(userId, code);
    }

    @Override
    public String retrieveCodeForUser(Long userId) {
        return userCodes.get(userId);
    }

    @Override
    public void invalidateCodeForUser(Long userId) {
        userCodes.remove(userId);
    }
}
