package com.juanba.sunnybank.domain.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthenticationRequest {

    @NotBlank(message = "Email is necessary for authenticate")
    private String email;

    @NotBlank(message = "Password is mandatory for authenticate")
    private String password;
}
