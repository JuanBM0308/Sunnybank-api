package com.juanba.sunnybank.infrastructure.persistance.user.repository;

import com.juanba.sunnybank.application.port.out.UserRepositoryOutPort;
import com.juanba.sunnybank.domain.model.user.User;
import com.juanba.sunnybank.infrastructure.mappers.UserMapper;
import com.juanba.sunnybank.infrastructure.persistance.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
