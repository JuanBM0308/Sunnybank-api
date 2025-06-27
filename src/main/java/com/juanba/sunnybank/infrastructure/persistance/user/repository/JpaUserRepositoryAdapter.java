package com.juanba.sunnybank.infrastructure.persistance.user.repository;

import com.juanba.sunnybank.application.port.out.UserRepositoryOutPort;
import com.juanba.sunnybank.domain.model.user.User;
import com.juanba.sunnybank.infrastructure.mappers.UserMapper;
import com.juanba.sunnybank.infrastructure.persistance.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaUserRepositoryAdapter implements UserRepositoryOutPort {

    private final SpringDataUserRepository springDataUserRepository;
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
}
