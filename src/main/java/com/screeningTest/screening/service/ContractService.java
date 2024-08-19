package com.screeningTest.screening.service;

import com.screeningTest.screening.dto.request.ApiResponse;
import com.screeningTest.screening.dto.request.contract.ContractCreationRequest;
import com.screeningTest.screening.dto.response.ContractResponse;
import com.screeningTest.screening.entity.Contract;
import com.screeningTest.screening.entity.Degree;
import com.screeningTest.screening.exception.AppException;
import com.screeningTest.screening.exception.ErrorCode;
import com.screeningTest.screening.mapper.IContractMapper;
import com.screeningTest.screening.repository.IContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContractService {
    @Autowired
    private IContractRepository contractRepository;
    @Autowired
    private IContractMapper contractMapper;

    //create
    public ContractResponse contractCreationRequeset(ContractCreationRequest request) {
        if (contractRepository.existsByContractName(request.getContractName())) {
            throw new AppException(ErrorCode.CONTRACT_EXISTED);
        }
        Contract contract = contractMapper.toContract(request);
        contract =contractRepository.save(contract);
        return contractMapper.toContractResponse(contract);
    }

    //update
    public ContractResponse contractUpdationRequeset(Long contractId, ContractCreationRequest request) {
        if (!contractRepository.existsByContractName(request.getContractName())) {
            throw new AppException(ErrorCode.CONTRACT_EXISTED);
        }
        Contract contract = contractRepository.save(contractMapper.toContract(request));
        return contractMapper.toContractResponse(contract);
    }

    //delete
    public void contractDeletionRequest(Long contractId) {
        contractRepository.deleteById(contractId);
    }

    //getContract
    public ContractResponse getContract(Long contractId) {
        return contractMapper.toContractResponse(contractRepository.findById(contractId).orElseThrow(() -> new AppException(ErrorCode.CONTRACT_NOT_FOUND)));
    }

    //getAll
    public List<Contract> getContracts() {
        List<Contract> contracts = contractRepository.findAll();
        if(contracts.isEmpty()) {
            throw new AppException(ErrorCode.CONTRACT_NOT_ITEM);
        }
        return contracts;
    }
}
