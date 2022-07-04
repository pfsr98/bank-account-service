package com.nttdata.bankaccountservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nttdata.bankaccountservice.util.Constants;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SavingAccountDto {

    private final String id;

    @NotNull(message = Constants.NOT_NULL)
    private final String accountNumber;

    @NotNull(message = Constants.NOT_NULL)
    private final String cci;

    @NotNull(message = Constants.NOT_NULL)
    private final Double balance;

    @NotNull(message = Constants.NOT_NULL)
    private final String personalCustomerId;

}
