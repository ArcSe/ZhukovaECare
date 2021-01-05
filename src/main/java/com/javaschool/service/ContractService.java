package com.javaschool.service;

import com.javaschool.dto.ContractDto;

import java.util.List;

public interface ContractService {

    List<ContractDto> getAll();

    void add(ContractDto contract);

    void delete(long id);

    void update(ContractDto contract);

    ContractDto getById(long id);
}
