package com.nttdata.bankaccountservice.controller;

import com.nttdata.bankaccountservice.dto.BankAccountDto;
import com.nttdata.bankaccountservice.dto.request.CheckingAccountDto;
import com.nttdata.bankaccountservice.dto.request.FixedTermAccountDto;
import com.nttdata.bankaccountservice.dto.request.GenerateReportDto;
import com.nttdata.bankaccountservice.dto.request.SavingsAccountDto;
import com.nttdata.bankaccountservice.service.IBankAccountService;
import com.nttdata.bankaccountservice.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constants.BANK_ACCOUNT_CONTROLLER)
public class BankAccountController {

    private final IBankAccountService service;

    @GetMapping(Constants.GET_ALL_METHOD)
    public Mono<ResponseEntity<Flux<BankAccountDto>>> getAll() {
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.getAll()));
    }

    @GetMapping(Constants.GET_BY_CUSTOMER_ID_METHOD)
    public Mono<ResponseEntity<Flux<BankAccountDto>>> getByCustomerId(@PathVariable(Constants.CUSTOMER_ID) String customerId) {
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.getByCustomerId(customerId)));
    }

    @GetMapping(Constants.GET_BY_ID_METHOD)
    public Mono<ResponseEntity<BankAccountDto>> getById(@PathVariable(Constants.ID) String id) {
        return service.getById(id).map(bankAccount -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bankAccount));
    }

    @GetMapping(Constants.GENERATE_REPORT)
    public Mono<ResponseEntity<BankAccountDto>> generateReport(@RequestBody GenerateReportDto generateReport) {
        return service.generateReport(generateReport).map(credit -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(credit));
    }

    @PostMapping(Constants.REGISTER_PERSONAL_SAVINGS_ACCOUNT_METHOD)
    public Mono<ResponseEntity<BankAccountDto>> registerPersonalSavingsAccount(@Valid @RequestBody SavingsAccountDto bankAccount, final ServerHttpRequest request) {
        return service.registerPersonalSavingsAccount(bankAccount)
                .map(registeredBankAccount -> ResponseEntity.created(URI.create(request.getURI() + Constants.SLASH + registeredBankAccount.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(registeredBankAccount));
    }

    @PostMapping(Constants.REGISTER_VIP_PERSONAL_SAVINGS_ACCOUNT_METHOD)
    public Mono<ResponseEntity<BankAccountDto>> registerVipPersonalSavingsAccount(@Valid @RequestBody SavingsAccountDto bankAccount, final ServerHttpRequest request) {
        return service.registerVipPersonalSavingsAccount(bankAccount)
                .map(registeredBankAccount -> ResponseEntity.created(URI.create(request.getURI() + Constants.SLASH + registeredBankAccount.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(registeredBankAccount));
    }

    @PostMapping(Constants.REGISTER_PERSONAL_FIXED_TERM_ACCOUNT_METHOD)
    public Mono<ResponseEntity<BankAccountDto>> registerPersonalFixedTermAccount(@Valid @RequestBody FixedTermAccountDto bankAccount, final ServerHttpRequest request) {
        return service.registerPersonalFixedTermAccount(bankAccount)
                .map(registeredBankAccount -> ResponseEntity.created(URI.create(request.getURI() + Constants.SLASH + registeredBankAccount.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(registeredBankAccount));
    }

    @PostMapping(Constants.REGISTER_PERSONAL_CHECKING_ACCOUNT_METHOD)
    public Mono<ResponseEntity<BankAccountDto>> registerPersonalCheckingAccount(@Valid @RequestBody CheckingAccountDto bankAccount, final ServerHttpRequest request) {
        return service.registerPersonalCheckingAccount(bankAccount)
                .map(registeredBankAccount -> ResponseEntity.created(URI.create(request.getURI() + Constants.SLASH + registeredBankAccount.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(registeredBankAccount));
    }

    @PostMapping(Constants.REGISTER_BUSINESS_CHECKING_ACCOUNT_METHOD)
    public Mono<ResponseEntity<BankAccountDto>> registerBusinessCheckingAccount(@Valid @RequestBody CheckingAccountDto bankAccount, final ServerHttpRequest request) {
        return service.registerBusinessCheckingAccount(bankAccount)
                .map(registeredBankAccount -> ResponseEntity.created(URI.create(request.getURI() + Constants.SLASH + registeredBankAccount.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(registeredBankAccount));
    }

    @PostMapping(Constants.REGISTER_PYME_BUSINESS_CHECKING_ACCOUNT_METHOD)
    public Mono<ResponseEntity<BankAccountDto>> registerPymeBusinessCheckingAccount(@Valid @RequestBody CheckingAccountDto bankAccount, final ServerHttpRequest request) {
        return service.registerPymeBusinessCheckingAccount(bankAccount)
                .map(registeredBankAccount -> ResponseEntity.created(URI.create(request.getURI() + Constants.SLASH + registeredBankAccount.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(registeredBankAccount));
    }

    @PutMapping(Constants.UPDATE_BY_ID_METHOD)
    public Mono<ResponseEntity<BankAccountDto>> updateById(@PathVariable(Constants.ID) String id, @Valid @RequestBody BankAccountDto bankAccount) {
        return service.updateById(id, bankAccount)
                .map(updatedBankAccount -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(updatedBankAccount));
    }

    @DeleteMapping(Constants.DELETE_BY_ID_METHOD)
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable(Constants.ID) String id) {
        return service.deleteById(id).thenReturn(new ResponseEntity<>(HttpStatus.OK));
    }

}
