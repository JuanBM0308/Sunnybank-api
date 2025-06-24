package com.juanba.sunnybank.infrastructure.persistance.user.repository;

import com.juanba.sunnybank.infrastructure.persistance.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
