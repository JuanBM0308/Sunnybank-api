package com.juanba.sunnybank.domain.model.bank_account;

import com.juanba.sunnybank.domain.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccount {
    private Long id;
    private String accountNumber;
    private User owner;
    private BigDecimal balance;
    private AccountType accountType;
    private Status accountStatus;
    private Currency currency;
    private LocalDateTime creationDate;
    private Long userId;
}
