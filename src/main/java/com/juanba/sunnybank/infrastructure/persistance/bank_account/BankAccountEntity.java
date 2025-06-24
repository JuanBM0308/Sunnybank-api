package com.juanba.sunnybank.infrastructure.persistance.bank_account;

import com.juanba.sunnybank.domain.model.bank_account.AccountType;
import com.juanba.sunnybank.domain.model.bank_account.Status;
import com.juanba.sunnybank.domain.model.user.User;
import com.juanba.sunnybank.infrastructure.persistance.user.entity.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_bank_account")
public class BankAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "account_number_ba")
    private String accountNumber;

    @NotNull
    @DecimalMin(value = "0.0")
    @Column(name = "balance_ba")
    private BigDecimal balance;

    @NotBlank(message = "Account type: account type is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(name = "account_type_ba")
    private AccountType accountType;

    @Column(name = "account_status")
    private Status accountStatus;

    @Column(name = "currency_ba")
    private Currency currency;

    @Column(name = "creation_date_ba")
    private LocalDateTime creationDate;

    // * Relacion UserEntity
    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "id_owner_ba")
    private User owner;
}
