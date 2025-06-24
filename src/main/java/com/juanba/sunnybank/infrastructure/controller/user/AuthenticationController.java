package com.juanba.sunnybank.infrastructure.controller.user;

import com.juanba.sunnybank.domain.request.user.AuthenticationRequest;
import com.juanba.sunnybank.domain.response.AuthenticationResponse;
import com.juanba.sunnybank.infrastructure.authentication.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authRequest) {
        AuthenticationResponse token = authenticationService.login(authRequest);
        return ResponseEntity.ok(token);
    }
}
