package com.javaschool.service.ipml;

import com.javaschool.dao.ContractDao;
import com.javaschool.dao.OptionDao;
import com.javaschool.dto.ContractDto;
import com.javaschool.mapper.ContractMapper;
import com.javaschool.model.Client;
import com.javaschool.model.Contract;
import com.javaschool.model.Option;
import com.javaschool.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService {
    private final ContractDao contractDao;
    private final OptionDao optionDao;
    private final ContractMapper contractMapper;


    @Autowired
    public ContractServiceImpl(ContractDao contractDao, OptionDao optionDao, ContractMapper contractMapper) {
        this.contractDao = contractDao;
        this.optionDao = optionDao;
        this.contractMapper = contractMapper;
    }


    @Override
    public List<ContractDto> getAll() {
        return contractDao.getAll().stream().map(contractMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void add(ContractDto contract) {
        contractDao.add(contractMapper.toEntity(contract));
    }

    @Override
    public void delete(long id) {
        contractDao.delete(id);
    }

    @Override
    public void update(ContractDto contract) {
        contractDao.update(contractMapper.toEntity(contract));
    }

    @Override
    public ContractDto getById(long id) {
        return contractMapper.toDto(contractDao.getById(id));
    }

    @Override
    public void lockedContract(long id) {
        Contract contract = contractDao.getById(id);
        if(!contract.isLockedByAdmin()) {
            contract.setLocked(!contract.isLocked());
            contractDao.update(contract);
        }
    }

    @Override
    public void lockedContractByAdmin(long id) {
        Contract contract = contractDao.getById(id);
        contract.setLockedByAdmin(!contract.isLockedByAdmin());
        contract.setLocked(!contract.isLocked());
        contractDao.update(contract);
    }

    @Override
    public void addOption(long optionId, long contractId) {
        Contract contract = contractDao.getById(contractId);
        Set<Option> options;
        if(!Objects.isNull(contract.getOptions())) {
            options = contract.getOptions();
        }
        else {
            options = new HashSet<>();
        }
        options.add(optionDao.getById(optionId));
        contract.setOptions(options);
        contractDao.update(contract);
    }

    @Override
    public void deleteOptions(long optionId, long contractId) {
        Contract contract = contractDao.getById(contractId);
        Option option = optionDao.getById(optionId);
        Set<Option> options = contract.getOptions();
        if (!options.contains(option)) {
            //todo: Exception EntityNotFound
        }
        contract.setOptions(options);
        contractDao.update(contract);
    }
}
