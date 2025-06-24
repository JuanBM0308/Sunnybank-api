package com.juanba.sunnybank.application.service;

import com.juanba.sunnybank.application.port.in.CreateUserUseCase;
import com.juanba.sunnybank.application.port.out.UserRepositoryOutPort;
import com.juanba.sunnybank.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements CreateUserUseCase {

    private final UserRepositoryOutPort userRepositoryOutPort;

    @Override
    public User createUser(User user) {
        return userRepositoryOutPort.save(user);
    }
}
