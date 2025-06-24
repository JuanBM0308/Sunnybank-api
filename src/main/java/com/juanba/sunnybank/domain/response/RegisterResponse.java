package com.juanba.sunnybank.domain.response;

public record RegisterResponse(
        Long id,
        Long identificationNumber,
        String firstname,
        String lastname,
        String email
) {

}
