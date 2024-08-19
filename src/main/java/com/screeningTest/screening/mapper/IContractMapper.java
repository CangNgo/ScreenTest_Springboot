package com.screeningTest.screening.mapper;

import com.screeningTest.screening.dto.request.contract.ContractCreationRequest;
import com.screeningTest.screening.dto.response.ContractResponse;
import com.screeningTest.screening.entity.Contract;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IContractMapper {
    Contract toContract(ContractCreationRequest contract);
    ContractResponse toContractResponse(Contract contract);
}
