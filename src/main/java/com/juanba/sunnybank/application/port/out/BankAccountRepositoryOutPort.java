package com.juanba.sunnybank.application.port.out;

import com.juanba.sunnybank.domain.model.bank_account.BankAccount;

import java.util.Optional;

public interface BankAccountRepositoryOutPort {
    BankAccount save(BankAccount bankAccount);

     Optional<BankAccount> findById(Long id);
}
