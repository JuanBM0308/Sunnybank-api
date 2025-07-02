package com.juanba.sunnybank.infrastructure.service.user;

import com.juanba.sunnybank.infrastructure.exceptions.InvalidCodePasswordException;
import org.springframework.stereotype.Service;

@Service
public class ChangePassword {

    private final GeneratePasswordCode generatePasswordCode;
    private final PasswordCodeStorage passwordCodeStorage;

    public ChangePassword(GeneratePasswordCode generatePasswordCode, PasswordCodeStorage passwordCodeStorage) {
        this.generatePasswordCode = generatePasswordCode;
        this.passwordCodeStorage = passwordCodeStorage;
    }

    public void setPassword(Long userId, String submittedCode) {
        final String storedCode = passwordCodeStorage.retrieveCodeForUser(userId);

        if (storedCode == null) {
            throw new InvalidCodePasswordException("No active code found for this user");
        }

        if (!storedCode.equals(submittedCode)) {
            throw new InvalidCodePasswordException("The provided code is incorrect.");
        }

        System.out.println("Code successfully verified for user: " + userId);

        passwordCodeStorage.invalidateCodeForUser(userId);
    }

    public String initiatePasswordReset(Long userId) {
        String newCode = generatePasswordCode.generateCode();
        passwordCodeStorage.storeCodeForUser(userId, newCode);
        System.out.println("Generated and stored code for user " + userId + ": " + newCode);
        return newCode;
    }
}
