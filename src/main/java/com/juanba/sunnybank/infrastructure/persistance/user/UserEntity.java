package com.juanba.sunnybank.infrastructure.persistance.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.juanba.sunnybank.domain.model.user.Address;
import com.juanba.sunnybank.domain.model.user.IdentificationType;
import com.juanba.sunnybank.domain.model.user.Role;
import com.juanba.sunnybank.infrastructure.persistance.bank_account.BankAccountEntity;
import com.juanba.sunnybank.infrastructure.persistance.notification.NotificationEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 20)
    @Column(name = "name_us")
    private String name;

    @NotNull
    @Column(name = "surname_us")
    private String surname;

    @NotBlank(message = "Identification type is mandatory")
    @Column(name = "identification_type_us")
    private IdentificationType identificationType;

    @NotBlank(message = "Identification number is mandatory")
    @Column(name = "identification_number_us")
    private Long identificationNumber;

    @Column(name = "register_date_us")
    private LocalDate registerDate;

    @Column(name = "lastLogin_date_us")
    private LocalDate lastLoginDate;

    @NotBlank(message = "Email is mandatory")
    @Column(name = "email_us", columnDefinition = "VARCHAR(500)", unique = true, nullable = false)
    private String email;

    @NotNull
    @NotBlank(message = "Phone number is mandatory")
    @Column(name = "phone_number_us")
    private String phoneNumber;

    @NotNull
    @NotBlank(message = "Address is mandatory")
    @Column(name = "address_us")
    private Address address;

    @NotBlank(message = "Password is mandatory")
    @Column(name = "password_us", columnDefinition = "TEXT", nullable = false)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role_us")
    private Role role;

    @NotNull
    @Column(name = "is_active_us")
    private boolean isActive;

    // * Relacion de UserEntity a BankAccountEntity
    @JsonIgnore
    @OneToMany(targetEntity = BankAccountEntity.class, fetch = FetchType.LAZY, mappedBy = "owner")
    private List<BankAccountEntity> bankAccounts;

    // * Relacion de UserEntity a NotificationEntity
    @JsonIgnore
    @OneToMany(targetEntity = NotificationEntity.class, fetch = FetchType.LAZY, mappedBy = "customer")
    private List<NotificationEntity> notifications;
}
