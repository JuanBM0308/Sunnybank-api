package com.juanba.sunnybank.application.service;

import com.juanba.sunnybank.application.port.in.user.CreateUserUseCase;
import com.juanba.sunnybank.application.port.in.user.GetUserUseCase;
import com.juanba.sunnybank.application.port.in.user.ListUserUseCase;
import com.juanba.sunnybank.application.port.out.UserRepositoryOutPort;
import com.juanba.sunnybank.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements CreateUserUseCase, GetUserUseCase, ListUserUseCase {

    private final UserRepositoryOutPort userRepositoryOutPort;

    @Override
    public User createUser(User user) {
        return userRepositoryOutPort.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepositoryOutPort.findById(id);
    }


    @Override
    public Page<User> findAllByIsActiveTrue(Pageable pageable) {
        return userRepositoryOutPort.findAllByIsActiveTrue(pageable);
    }
}
