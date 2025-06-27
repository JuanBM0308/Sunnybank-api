package com.juanba.sunnybank.application.port.in.user;

import com.juanba.sunnybank.domain.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListUserUseCase {
    Page<User> findAllByIsActiveTrue(Pageable pageable);
}
