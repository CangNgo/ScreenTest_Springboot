package com.screeningTest.screening.controller;

import com.screeningTest.screening.dto.request.ApiResponse;
import com.screeningTest.screening.dto.request.account.AccountCreationRequest;
import com.screeningTest.screening.dto.response.AccountResponse;
import com.screeningTest.screening.service.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/account")
public class AccountController {
    AccountService accountService;
    @PostMapping
    ApiResponse<AccountResponse> crateAccount(@RequestBody AccountCreationRequest request){
        return  ApiResponse.<AccountResponse>builder()
                .result(accountService.createAccount(request))
                .build();
    }
}
