package com.juanba.sunnybank.infrastructure.authentication;

import com.juanba.sunnybank.domain.request.user.AuthenticationRequest;
import com.juanba.sunnybank.domain.response.AuthenticationResponse;
import com.juanba.sunnybank.infrastructure.persistance.user.entity.UserEntity;
import com.juanba.sunnybank.infrastructure.persistance.user.repository.SpringDataUserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final SpringDataUserRepository springDataUserRepository;
    private final JwtService jwtService;

    public AuthenticationResponse login(@Valid AuthenticationRequest authRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(), authRequest.getPassword()
        );

        authenticationManager.authenticate(authToken);

        UserEntity user = springDataUserRepository.findByEmail(authRequest.getEmail()).get();

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));

        return new AuthenticationResponse(jwt);
    }

    public Map<String, Object> generateExtraClaims(UserEntity user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("email", user.getEmail());
        extraClaims.put("role", user.getRole());

        return extraClaims;
    }
}
