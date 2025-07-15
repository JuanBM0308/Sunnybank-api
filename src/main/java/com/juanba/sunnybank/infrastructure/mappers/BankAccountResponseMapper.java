package com.juanba.sunnybank.infrastructure.mappers;

import com.juanba.sunnybank.domain.model.bank_account.BankAccount;
import com.juanba.sunnybank.domain.request.bank_account.OpenBankAccountRequest;
import com.juanba.sunnybank.domain.response.bank_account.AccountOpeningResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BankAccountResponseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "accountType", target = "accountType")
    @Mapping(source = "currency", target = "currency")
    @Mapping(source = "owner", target = "userId")
    BankAccount toBankAccount(OpenBankAccountRequest request);

    @Mapping(source = "accountType", target = "accountType")
    @Mapping(source = "currency", target = "currency")
    @Mapping(source = "owner", target = "userId")
    AccountOpeningResponse toRegisterResponse(BankAccount bankAccount);
}
