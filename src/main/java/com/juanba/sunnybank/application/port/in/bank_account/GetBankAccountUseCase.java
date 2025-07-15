package com.juanba.sunnybank.application.port.in.bank_account;

import com.juanba.sunnybank.domain.model.bank_account.BankAccount;

import java.util.Optional;

public interface GetBankAccountUseCase {
    Optional<BankAccount> findById(Long id);
}
