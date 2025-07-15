package com.juanba.sunnybank.application.port.in.bank_account;

import com.juanba.sunnybank.domain.model.bank_account.BankAccount;

public interface CreateBankAccountUseCase {
    BankAccount create(BankAccount bankAccount);
}
