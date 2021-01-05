package com.javaschool.mapper;

import com.javaschool.dto.ContractDto;
import com.javaschool.model.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContractMapper extends AbstractMapper<Contract, ContractDto> {
    @Autowired
    ContractMapper() {
        super(Contract.class, ContractDto.class);
    }
}
