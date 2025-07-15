package com.juanba.sunnybank.domain.request.bank_account;

import com.juanba.sunnybank.domain.model.bank_account.AccountType;
import com.juanba.sunnybank.domain.model.user.User;
import jakarta.validation.constraints.NotNull;

import java.util.Currency;

public record OpenBankAccountRequest(
        AccountType accountType,
        Currency currency,

        @NotNull
        User owner
) {
}
