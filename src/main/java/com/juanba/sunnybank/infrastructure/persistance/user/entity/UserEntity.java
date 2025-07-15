package com.juanba.sunnybank.infrastructure.persistance.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.juanba.sunnybank.domain.model.user.IdentificationType;
import com.juanba.sunnybank.domain.model.user.Role;
import com.juanba.sunnybank.domain.request.user.UpdateUserRequest;
import com.juanba.sunnybank.infrastructure.persistance.address.AddressEntity;
import com.juanba.sunnybank.infrastructure.persistance.bank_account.entity.BankAccountEntity;
import com.juanba.sunnybank.infrastructure.persistance.notification.NotificationEntity;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 20)
    @Column(name = "name_us")
    private String name;

    @NotNull
    @Column(name = "surname_us")
    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(name = "identification_type_us")
    private IdentificationType identificationType;

    @Column(name = "identification_number_us")
    private Long identificationNumber;

    @Column(name = "register_date_us")
    private LocalDate registerDate;

    @Column(name = "lastLogin_date_us")
    private LocalDate lastLoginDate;

    @Email
    @Column(name = "email_us", columnDefinition = "VARCHAR(500)", unique = true, nullable = false)
    private String email;

    @NotNull
    @Column(name = "phone_number_us")
    private String phoneNumber;

    // * Incrustacion a AddressEntity
    @Embedded
    private AddressEntity address;

    @Column(name = "password_us", columnDefinition = "TEXT", nullable = false)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role_us")
    private Role role;

    @NotNull
    @Column(name = "is_active_us")
    private boolean isActive;

    // * Relacion a BankAccountEntity
    @JsonIgnore
    @OneToMany(targetEntity = BankAccountEntity.class, fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BankAccountEntity> bankAccounts;

    // * Relacion a NotificationEntity
    @JsonIgnore
    @OneToMany(targetEntity = NotificationEntity.class, fetch = FetchType.LAZY, mappedBy = "customer")
    private List<NotificationEntity> notifications;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // ! Metodo de eliminacion logica
    public void delete() {
        this.isActive = false;
    }

    public void updateInformation(@Valid UpdateUserRequest updateUserRequest) {
        if (updateUserRequest.email() != null) {
            this.email = updateUserRequest.email();
        }
        if (updateUserRequest.phoneNumber() != null) {
            this.phoneNumber = updateUserRequest.phoneNumber();
        }
        if (updateUserRequest.address() != null) {
            this.address = new AddressEntity(
                    updateUserRequest.address().getStreet(),
                    updateUserRequest.address().getCity(),
                    updateUserRequest.address().getState(),
                    updateUserRequest.address().getZipCode()
            );
        }
    }
}

