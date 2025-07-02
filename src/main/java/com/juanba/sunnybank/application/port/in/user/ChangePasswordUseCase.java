package com.juanba.sunnybank.application.port.in.user;

import com.juanba.sunnybank.domain.request.user.ChangePasswordRequest;

public interface ChangePasswordUseCase {
    void changePassword(ChangePasswordRequest changePasswordRequest);
}
