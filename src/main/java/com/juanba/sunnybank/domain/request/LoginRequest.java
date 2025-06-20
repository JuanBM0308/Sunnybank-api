package com.juanba.sunnybank.domain.request;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {

    @Pattern(regexp = "^(.+)@(\\\\S+)$",
            message = "Email: must be a valid email address")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*()]).{8,}$",
            message = "Password: Minimum eight characters, at least one lower and upper case letter, and one special character")
    private String password;
}
