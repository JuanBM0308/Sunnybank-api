package com.juanba.sunnybank.infrastructure.persistance.user.repository;

import com.juanba.sunnybank.application.port.out.UserRepositoryOutPort;
import com.juanba.sunnybank.domain.model.user.User;
import com.juanba.sunnybank.domain.request.user.ChangePasswordRequest;
import com.juanba.sunnybank.domain.request.user.UpdateUserRequest;
import com.juanba.sunnybank.infrastructure.mappers.UserMapper;
import com.juanba.sunnybank.infrastructure.persistance.user.entity.UserEntity;
import com.juanba.sunnybank.infrastructure.service.user.ChangePassword;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaUserRepositoryAdapter implements UserRepositoryOutPort {

    private final SpringDataUserRepository springDataUserRepository;

    private final ChangePassword changePassword;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        final UserEntity savedUser = springDataUserRepository.save(userEntity);
        return userMapper.toDomain(savedUser);
    }

    @Override
    public Optional<User> findById(Long id) {
        final UserEntity savedUser = springDataUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found in the databases"));
        return Optional.of(userMapper.toDomain(savedUser));
    }

    @Override
    public Page<User> findAllByIsActiveTrue(Pageable pageable) {
        final Page<UserEntity> userEntityPage = springDataUserRepository.findAllByIsActiveTrue(pageable);
        return userEntityPage.map(userMapper::toDomain);
    }

    @Override
    public void delete(Long id) {
        final UserEntity user = springDataUserRepository.getReferenceById(id);
        user.delete();
        userMapper.toDomain(user);
    }

    @Override
    public User update(UpdateUserRequest updateUserRequest) {
        final UserEntity user = springDataUserRepository.findById(updateUserRequest.id())
                .orElseThrow(() -> new IllegalArgumentException("User with ID" + updateUserRequest.id() + " not found"));
        user.updateInformation(updateUserRequest);
        return userMapper.toDomain(user);
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        final UserEntity user = springDataUserRepository.findById(changePasswordRequest.userId())
                .orElseThrow(() -> new IllegalArgumentException("User with ID" + changePasswordRequest.userId() + " not found"));

        changePassword.setPassword(changePasswordRequest.userId(),changePasswordRequest.passwordCode());
        user.setPassword(passwordEncoder.encode(changePasswordRequest.password()));

        userMapper.toDomain(user);
    }
}
