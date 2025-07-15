package com.juanba.sunnybank.infrastructure.persistance.bank_account.repository;

import com.juanba.sunnybank.application.port.out.BankAccountRepositoryOutPort;
import com.juanba.sunnybank.domain.model.bank_account.BankAccount;
import com.juanba.sunnybank.infrastructure.mappers.BankAccountMapper;
import com.juanba.sunnybank.infrastructure.persistance.bank_account.entity.BankAccountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaBankAccountRepositoryAdapter implements BankAccountRepositoryOutPort {

    private final SpringDataBankAccountRepository springDataBankAccountRepository;

    private final BankAccountMapper bankAccountMapper;


    @Override
    public BankAccount save(BankAccount bankAccount) {
        BankAccountEntity bankAccountEntity = bankAccountMapper.toEntity(bankAccount);
        final BankAccountEntity bankAccountSaved = springDataBankAccountRepository.save(bankAccountEntity);
        return bankAccountMapper.toDomain(bankAccountSaved);
    }

    @Override
    public Optional<BankAccount> findById(Long id) {
        final BankAccountEntity bankAccountEntity = springDataBankAccountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bank account not found in the databases"));
        return Optional.of(bankAccountMapper.toDomain(bankAccountEntity));
    }
}
