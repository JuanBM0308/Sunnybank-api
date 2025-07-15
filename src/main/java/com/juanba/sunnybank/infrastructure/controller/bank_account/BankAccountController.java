package com.juanba.sunnybank.infrastructure.controller.bank_account;

import com.juanba.sunnybank.application.port.in.bank_account.CreateBankAccountUseCase;
import com.juanba.sunnybank.application.port.in.bank_account.GetBankAccountUseCase;
import com.juanba.sunnybank.domain.model.bank_account.BankAccount;
import com.juanba.sunnybank.domain.request.bank_account.OpenBankAccountRequest;
import com.juanba.sunnybank.domain.response.bank_account.AccountOpeningResponse;
import com.juanba.sunnybank.domain.response.bank_account.GetBankAccountResponse;
import com.juanba.sunnybank.infrastructure.mappers.BankAccountResponseMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bank-accounts")
public class BankAccountController {

    private final CreateBankAccountUseCase createBankAccountUseCase;
    private final GetBankAccountUseCase getBankAccountUseCase;

    private final BankAccountResponseMapper bankAccountResponseMapper;

    @Transactional
    @PostMapping
    public ResponseEntity<AccountOpeningResponse> accountOpening(@RequestBody @Valid OpenBankAccountRequest openBankAccountRequest, UriComponentsBuilder uriComponentsBuilder) {
        final BankAccount bankAccount = bankAccountResponseMapper.toBankAccount(openBankAccountRequest);
        final BankAccount bankAccountCreated = createBankAccountUseCase.create(bankAccount);

        var uri = uriComponentsBuilder.path("/bank-accounts").buildAndExpand(bankAccountCreated.getId()).toUri();

        return ResponseEntity.created(uri).body(bankAccountResponseMapper.toRegisterResponse(bankAccountCreated));
    }

    @GetMapping
    public ResponseEntity<GetBankAccountResponse> getBankAccount(@PathVariable Long id) {
        final BankAccount bankAccount = getBankAccountUseCase.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bank account not found"));

        return ResponseEntity.ok(new GetBankAccountResponse(bankAccount));
    }
    
}
