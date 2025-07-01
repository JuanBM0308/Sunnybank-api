package com.juanba.sunnybank.application.port.in.user;

import com.juanba.sunnybank.domain.model.user.User;
import com.juanba.sunnybank.domain.request.user.UpdateUserRequest;

public interface UpdateUserUseCase {
    User update(UpdateUserRequest updateUserRequest);
}
