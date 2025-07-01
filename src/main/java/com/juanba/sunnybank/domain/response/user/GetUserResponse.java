package com.juanba.sunnybank.domain.response.user;

import com.juanba.sunnybank.domain.model.user.User;

import java.time.LocalDate;

public record GetUserResponse(
        Long id,
        String name,
        String surname,
        String email,
        String phoneNumber,
        LocalDate registerDate,
        LocalDate lastLoginDate,
        boolean isActive
) {
    public GetUserResponse(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getRegisterDate(),
                user.getLastLoginDate(),
                user.isActive()
        );
    }
}
