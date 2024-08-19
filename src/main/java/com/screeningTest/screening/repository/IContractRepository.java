package com.screeningTest.screening.repository;

import com.screeningTest.screening.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContractRepository extends JpaRepository<Contract, Long> {
    boolean existsByContractName(String contractName);
}
