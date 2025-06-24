package com.juanba.sunnybank.infrastructure.controller.user;

import com.juanba.sunnybank.application.port.in.CreateUserUseCase;
import com.juanba.sunnybank.domain.model.user.User;
import com.juanba.sunnybank.domain.request.user.RegisterRequest;
import com.juanba.sunnybank.domain.response.RegisterResponse;
import com.juanba.sunnybank.infrastructure.mappers.UserRequestResponseMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final UserRequestResponseMapper userRequestResponseMapper;

    @Transactional
    @PostMapping("/user")
    public RegisterResponse createUser(@RequestBody @Valid RegisterRequest registerRequest) {
        final User user = userRequestResponseMapper.toUser(registerRequest);
        final User userCreated =  createUserUseCase.createUser(user);
        return userRequestResponseMapper.toRegisterResponse(userCreated);
    }
}
