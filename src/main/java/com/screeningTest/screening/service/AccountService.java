package com.screeningTest.screening.service;

import com.screeningTest.screening.dto.request.account.AccountCreationRequest;
import com.screeningTest.screening.dto.response.AccountResponse;
import com.screeningTest.screening.entity.Account;
import com.screeningTest.screening.enums.Role;
import com.screeningTest.screening.exception.AppException;
import com.screeningTest.screening.exception.ErrorCode;
import com.screeningTest.screening.mapper.IAccountMapper;
import com.screeningTest.screening.repository.IAccountReponsitory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AccountService {
    IAccountReponsitory accountReponsitory;
    IAccountMapper accountMapper;
    public AccountResponse createAccount(AccountCreationRequest request) {
        if(accountReponsitory.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        Account account = accountMapper.toAccount(request);

        // ma hoa mat khau
        PasswordEncoder encoder = new BCryptPasswordEncoder(10);
        account.setPassword(encoder.encode(request.getPassword()));
        account = accountReponsitory.save(account);
       return accountMapper.toAccountResponse(account);
    }
}
