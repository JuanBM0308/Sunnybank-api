package com.juanba.sunnybank.domain.request.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ChangePasswordRequest(
        @NotNull
        Long userId,

        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*()]).{8,}$",
                message = "Password: Minimum eight characters, at least one lower and upper case letter, and one special character")
        String password,

        @NotNull
        String passwordCode
) {
}
