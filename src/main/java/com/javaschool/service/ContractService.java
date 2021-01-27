package com.javaschool.service;

import com.javaschool.dto.ContractDto;
import com.javaschool.exception.notFound.ExamplesNotFoundException;
import com.javaschool.exception.notFound.NotDataFoundException;

import java.util.List;

public interface ContractService {

    List<ContractDto> getAll() throws NotDataFoundException;

    void add(ContractDto contract);

    void delete(long id) throws ExamplesNotFoundException;

    void update(ContractDto contract);

    ContractDto getById(long id) throws ExamplesNotFoundException;

    void lockedContract(long id) throws ExamplesNotFoundException;

    void lockedContractByAdmin(long id) throws ExamplesNotFoundException;

    void addOption(long optionId, long contractId) throws ExamplesNotFoundException;

    void deleteOptions(long optionId, long contractId) throws Exception;
}
