package com.juanba.sunnybank.infrastructure.controller.user;

import com.juanba.sunnybank.domain.request.user.AuthenticationRequest;
import com.juanba.sunnybank.domain.response.AuthenticationResponse;
import com.juanba.sunnybank.infrastructure.persistance.user.entity.UserEntity;
import com.juanba.sunnybank.infrastructure.util.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authRequest) {
        var authToken = new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password());
        var authentication = authenticationManager.authenticate(authToken);

        var jwt = jwtService.generateToken((UserEntity) authentication.getPrincipal());

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
