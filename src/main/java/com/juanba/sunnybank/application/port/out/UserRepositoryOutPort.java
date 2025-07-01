package com.juanba.sunnybank.application.port.out;

import com.juanba.sunnybank.domain.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepositoryOutPort {
    User save(User user);
    Optional<User> findById(Long id);
    Page<User> findAllByIsActiveTrue(Pageable pageable);
    void delete(Long id);
}
