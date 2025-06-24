package com.juanba.sunnybank.application.port.out;

import com.juanba.sunnybank.domain.model.user.User;

public interface UserRepositoryOutPort {
    User save(User user);
}
