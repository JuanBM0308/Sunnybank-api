package com.juanba.sunnybank.domain.response.user;

public record RegisterResponse(
        Long id,
        Long identificationNumber,
        String firstname,
        String lastname,
        String email
) {

}
