package com.juanba.sunnybank.infrastructure.persistance.user.repository;

import com.juanba.sunnybank.infrastructure.persistance.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {
    UserDetails findByEmail(String email);
    Page<UserEntity> findAllByIsActiveTrue(Pageable pageable);
}
