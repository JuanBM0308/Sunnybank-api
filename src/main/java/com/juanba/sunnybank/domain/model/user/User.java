package com.juanba.sunnybank.domain.model.user;

import com.juanba.sunnybank.domain.model.address.Address;
import com.juanba.sunnybank.domain.model.bank_account.BankAccount;
import com.juanba.sunnybank.domain.model.notification.Notification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    private String name;
    private String surname;
    private IdentificationType identificationType;
    private Long identificationNumber;
    private LocalDate registerDate;
    private LocalDate lastLoginDate;
    private String email;
    private String phoneNumber;
    private Address address;
    private String password;
    private Role role;
    private boolean isActive;
    private List<BankAccount> bankAccounts;
    private List<Notification> notifications;

    public User(Long id, String name, String surname, IdentificationType identificationType, Long identificationNumber, LocalDate registerDate, LocalDate lastLoginDate, String email, String phoneNumber, Address address, String password, Role role, boolean isActive) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.identificationType = identificationType;
        this.identificationNumber = identificationNumber;
        this.registerDate = registerDate;
        this.lastLoginDate = lastLoginDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
    }
}
