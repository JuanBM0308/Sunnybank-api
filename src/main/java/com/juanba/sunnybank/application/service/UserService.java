package com.juanba.sunnybank.application.service;

import com.juanba.sunnybank.application.port.in.user.*;
import com.juanba.sunnybank.application.port.out.UserRepositoryOutPort;
import com.juanba.sunnybank.domain.model.user.User;
import com.juanba.sunnybank.domain.request.user.ChangePasswordRequest;
import com.juanba.sunnybank.domain.request.user.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements CreateUserUseCase, GetUserUseCase, ListUserUseCase, DeleteUserUserCase, UpdateUserUseCase, ChangePasswordUseCase {

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

    @Override
    public void delete(Long id) {
        userRepositoryOutPort.delete(id);
    }

    @Override
    public User update(UpdateUserRequest updateUserRequest) {
        return userRepositoryOutPort.update(updateUserRequest);
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        userRepositoryOutPort.changePassword(changePasswordRequest);
    }
}
