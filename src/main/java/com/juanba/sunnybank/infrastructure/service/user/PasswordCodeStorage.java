package com.juanba.sunnybank.infrastructure.service.user;

public interface PasswordCodeStorage {
    void storeCodeForUser(Long userId, String code);
    String retrieveCodeForUser(Long userId);
    void invalidateCodeForUser(Long userId);
}
