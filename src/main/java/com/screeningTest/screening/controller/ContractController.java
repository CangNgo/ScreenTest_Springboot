package com.screeningTest.screening.controller;

import com.screeningTest.screening.dto.request.ApiResponse;
import com.screeningTest.screening.dto.request.contract.ContractCreationRequest;
import com.screeningTest.screening.dto.response.ContractResponse;
import com.screeningTest.screening.dto.response.DegreeResponse;
import com.screeningTest.screening.mapper.IContractMapper;
import com.screeningTest.screening.service.ContractService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/contract")
public class ContractController {
    ContractService contractService;
    IContractMapper contractMapper;
    @GetMapping("/{contractId}")
    public ApiResponse<ContractResponse> getContract(@PathVariable("contractId") Long contractId) {
        ApiResponse apiResponse = new ApiResponse<>();
        ContractResponse contract = contractService.getContract(contractId) ;
        apiResponse.setResult(contract);
        return apiResponse;
    }

    @GetMapping
    public ApiResponse<DegreeResponse> getContracts() {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setResult(contractService.getContracts());
        return apiResponse;
    }

    @PostMapping
    public ApiResponse<ContractResponse> createContract(@RequestBody ContractCreationRequest request){
        ApiResponse<ContractResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(contractService.contractCreationRequeset(request));
        return apiResponse;
    }

    @DeleteMapping("/{contractId}")
    public ApiResponse<String> deleteContract(@PathVariable("contractId") Long contractId) {
        contractService.contractDeletionRequest(contractId);
        return ApiResponse.<String>builder()
                .result("contract has deleted")
                .build();
    }
}
