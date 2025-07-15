package com.juanba.sunnybank.infrastructure.persistance.bank_account.repository;

import com.juanba.sunnybank.infrastructure.persistance.bank_account.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataBankAccountRepository extends JpaRepository<BankAccountEntity, Long> {
}
