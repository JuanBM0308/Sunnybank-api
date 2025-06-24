package com.juanba.sunnybank.application.port.in;

import com.juanba.sunnybank.domain.model.user.User;

public interface CreateUserUseCase {
    User createUser(User user);
}
