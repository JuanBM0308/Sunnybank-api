package com.juanba.sunnybank.domain.request.user;

import com.juanba.sunnybank.domain.model.address.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UpdateUserRequest(
        @NotNull
        Long id,
        String email,
        String phoneNumber,
        @Valid
        Address address
) {
}
