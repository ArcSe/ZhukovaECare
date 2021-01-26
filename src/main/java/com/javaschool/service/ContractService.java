package com.javaschool.service;

import com.javaschool.dto.ContractDto;

import java.util.List;

public interface ContractService {

    List<ContractDto> getAll();

    void add(ContractDto contract);

    void delete(long id);

    void update(ContractDto contract);

    ContractDto getById(long id);

    void lockedContract(long id);

    void lockedContractByAdmin(long id);

    void addOption(long optionId, long contractId);

    void deleteOptions(long optionId, long contractId);
}
