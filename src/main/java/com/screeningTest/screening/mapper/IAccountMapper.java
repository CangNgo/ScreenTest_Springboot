package com.screeningTest.screening.mapper;

import com.screeningTest.screening.dto.request.account.AccountCreationRequest;
import com.screeningTest.screening.dto.response.AccountResponse;
import com.screeningTest.screening.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAccountMapper {
    Account toAccount(AccountCreationRequest acc);
    AccountResponse toAccountResponse(Account acc);
}
