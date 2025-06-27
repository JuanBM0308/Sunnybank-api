package com.juanba.sunnybank.application.port.in.user;

import com.juanba.sunnybank.domain.model.user.User;

import java.util.Optional;

public interface GetUserUseCase {
    Optional<User> findById(Long id);
}
