package com.juanba.sunnybank.infrastructure.controller.user;

import com.juanba.sunnybank.application.port.in.user.CreateUserUseCase;
import com.juanba.sunnybank.application.port.in.user.DeleteUserUserCase;
import com.juanba.sunnybank.application.port.in.user.GetUserUseCase;
import com.juanba.sunnybank.application.port.in.user.ListUserUseCase;
import com.juanba.sunnybank.domain.model.user.User;
import com.juanba.sunnybank.domain.request.user.RegisterRequest;
import com.juanba.sunnybank.domain.response.user.GetUserResponse;
import com.juanba.sunnybank.domain.response.user.RegisterResponse;
import com.juanba.sunnybank.infrastructure.mappers.UserRequestResponseMapper;
import com.juanba.sunnybank.infrastructure.persistance.user.entity.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final ListUserUseCase listUserUseCase;
    private final DeleteUserUserCase deleteUserUserCase;

    private final UserRequestResponseMapper userRequestResponseMapper;

    @Transactional
    @PostMapping
    public ResponseEntity<RegisterResponse> createUser(@RequestBody @Valid RegisterRequest registerRequest, UriComponentsBuilder uriComponentsBuilder) {
        final User user = userRequestResponseMapper.toUser(registerRequest);
        final User userCreated = createUserUseCase.createUser(user);

        var uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(userCreated.getId()).toUri();

        return ResponseEntity.created(uri).body(userRequestResponseMapper.toRegisterResponse(userCreated));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable Long id) {
        final User user = getUserUseCase.findById(id).
                orElseThrow(() -> new IllegalArgumentException("User not found in the database"));

        return ResponseEntity.ok(new GetUserResponse(user));
    }

    @GetMapping
    public ResponseEntity<Page<GetUserResponse>> listUser(@PageableDefault(sort = {"email"}) Pageable pageable) {
        final Page<GetUserResponse> userPage = listUserUseCase.findAllByIsActiveTrue(pageable)
                .map(GetUserResponse::new);

        return ResponseEntity.ok(userPage);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        deleteUserUserCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
