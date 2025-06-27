package com.juanba.sunnybank.domain.request.user;

public record AuthenticationRequest(
        String email,
        String password
) {
}
