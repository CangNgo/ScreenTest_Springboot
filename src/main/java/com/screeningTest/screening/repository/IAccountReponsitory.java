package com.screeningTest.screening.repository;

import com.screeningTest.screening.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAccountReponsitory extends JpaRepository<Account, Long> {
    boolean existsByUsername(String username);
    Optional<Account> findByUsername(String username);
}
