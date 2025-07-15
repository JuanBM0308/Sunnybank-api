package com.juanba.sunnybank.domain.response.bank_account;

import com.juanba.sunnybank.domain.model.bank_account.AccountType;
import com.juanba.sunnybank.domain.model.bank_account.BankAccount;
import com.juanba.sunnybank.domain.model.bank_account.Status;

import java.time.LocalDateTime;
import java.util.Currency;

public record GetBankAccountResponse(
        Long id,
        AccountType accountType,
        Status accountStatus,
        Currency currency,
        LocalDateTime creationDate,
        Long owner
) {
    public GetBankAccountResponse(BankAccount bankAccount) {
        this(
                bankAccount.getId(),
                bankAccount.getAccountType(),
                bankAccount.getAccountStatus(),
                bankAccount.getCurrency(),
                bankAccount.getCreationDate(),
                bankAccount.getUserId()
        );
    }
}
