package com.juanba.sunnybank.domain.request;

import com.juanba.sunnybank.domain.model.user.Address;
import com.juanba.sunnybank.domain.model.user.IdentificationType;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    private Long identificationNumber;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*()]).{8,}$",
            message = "Password: Minimum eight characters, at least one lower and upper case letter, and one special character")
    private String email;

    @Size(max = 10, message = "Phone number: must contain a maximum of 10 characters")
    private String phoneNumber;

    private Address address;

    @Pattern(regexp = "^(.+)@(\\\\S+)$",
            message = "Email: must be a valid email address")
    private String password;
}
