package com.juanba.sunnybank.domain.request.user;

import com.juanba.sunnybank.domain.model.address.Address;
import com.juanba.sunnybank.domain.model.user.IdentificationType;
import com.juanba.sunnybank.domain.model.user.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    @Size(min = 2, max = 20, message = "Name: must be between 2 and 20 characters")
    private String firstname;

    @Size(min = 2, max = 20, message = "Surname: must be between 2 and 20 characters")
    private String lastname;

    private IdentificationType identificationType;

    @NotNull
    private Long identificationNumber;

    @Email
    private String email;

    @Size(max = 10, message = "Phone number: must contain a maximum of 10 characters")
    private String phoneNumber;

    @Valid
    private Address address;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*()]).{8,}$",
            message = "Password: Minimum eight characters, at least one lower and upper case letter, and one special character")
    private String password;

    @NotNull
    private Role role;
}
