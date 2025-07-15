package com.juanba.sunnybank.domain.response.bank_account;

import com.juanba.sunnybank.domain.model.bank_account.AccountType;
import com.juanba.sunnybank.domain.model.user.User;

import java.util.Currency;

public record AccountOpeningResponse(
        AccountType accountType,
        Currency currency,
        User owner
) {
}
