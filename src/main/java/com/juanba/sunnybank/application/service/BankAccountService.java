package com.juanba.sunnybank.application.service;

import com.juanba.sunnybank.application.port.in.bank_account.CreateBankAccountUseCase;
import com.juanba.sunnybank.application.port.in.bank_account.GetBankAccountUseCase;
import com.juanba.sunnybank.application.port.out.BankAccountRepositoryOutPort;
import com.juanba.sunnybank.domain.model.bank_account.BankAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankAccountService implements CreateBankAccountUseCase, GetBankAccountUseCase {

    private final BankAccountRepositoryOutPort bankAccountRepositoryOutPort;


    @Override
    public BankAccount create(BankAccount bankAccount) {
        return bankAccountRepositoryOutPort.save(bankAccount);
    }

    @Override
    public Optional<BankAccount> findById(Long id) {
        return bankAccountRepositoryOutPort.findById(id);
    }
}
