package com.juanba.sunnybank.infrastructure.mappers;

import com.juanba.sunnybank.domain.model.bank_account.BankAccount;
import com.juanba.sunnybank.infrastructure.persistance.bank_account.BankAccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BankAccountMapper {
    BankAccount toDomain(BankAccountEntity entity);
    BankAccountEntity toEntity(BankAccount domain);

    List<BankAccount> toDomainList(List<BankAccountEntity> entities);
    List<BankAccountEntity> toEntityList(List<BankAccount> domains);
}
