package com.javaschool.service.ipml;

import com.javaschool.dao.ContractDao;
import com.javaschool.dao.OptionDao;
import com.javaschool.dto.ContractDto;
import com.javaschool.dto.OptionDto;
import com.javaschool.dto.TariffDto;
import com.javaschool.exception.notFound.ExamplesNotFoundException;
import com.javaschool.exception.notFound.NotDataFoundException;
import com.javaschool.mapper.ContractMapper;
import com.javaschool.model.Client;
import com.javaschool.model.Contract;
import com.javaschool.model.Option;
import com.javaschool.model.Tariff;
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
    public List<ContractDto> getAll() throws NotDataFoundException {
        if(Objects.isNull(contractDao.getAll())){
            throw new NotDataFoundException(Contract.class.getName());
        }
        return contractDao.getAll().stream().map(contractMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void add(ContractDto contract) {
        TariffDto tariffDto = contract.getTariff();
        contract.setOptions(tariffDto.getOptions());
        contractDao.add(contractMapper.toEntity(contract));
    }

    @Override
    public void delete(long id) throws ExamplesNotFoundException {
        if(Objects.isNull(contractDao.getById(id))){
            throw new ExamplesNotFoundException(id);
        }
        contractDao.delete(id);
    }

    @Override
    public void update(ContractDto contract) {
        if(!Objects.isNull(contract.getTariff())) {
            TariffDto tariffDto = contract.getTariff();
            if(!Objects.isNull(tariffDto.getOptions())) {
                Set<OptionDto> optionDtos = tariffDto.getOptions();
                contract.setOptions(optionDtos);
            }
        }
        contractDao.update(contractMapper.toEntity(contract));
    }

    @Override
    public ContractDto getById(long id) throws ExamplesNotFoundException {
        if(Objects.isNull(contractDao.getById(id))){
            throw new ExamplesNotFoundException(id);
        }
        return contractMapper.toDto(contractDao.getById(id));
    }

    @Override
    public void lockedContract(long id) throws ExamplesNotFoundException {
        if(Objects.isNull(contractDao.getById(id))){
            throw new ExamplesNotFoundException(id);
        }
        Contract contract = contractDao.getById(id);
        if(!contract.isLockedByAdmin()) {
            contract.setLocked(!contract.isLocked());
            contractDao.update(contract);
        }
    }

    @Override
    public void lockedContractByAdmin(long id) throws ExamplesNotFoundException {
        if(Objects.isNull(contractDao.getById(id))){
            throw new ExamplesNotFoundException(id);
        }
        Contract contract = contractDao.getById(id);
        contract.setLockedByAdmin(!contract.isLockedByAdmin());
        contract.setLocked(!contract.isLocked());
        contractDao.update(contract);
    }

    @Override
    public void addOption(long optionId, long contractId) throws ExamplesNotFoundException {
        if(Objects.isNull(contractDao.getById(contractId))){
            throw new ExamplesNotFoundException(contractId);
        }
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
    public void deleteOptions(long optionId, long contractId) throws Exception {
        if(Objects.isNull(contractDao.getById(contractId))){
            throw new ExamplesNotFoundException(contractId);
        }
        if(Objects.isNull(optionDao.getById(optionId))){
            throw new ExamplesNotFoundException(optionId);
        }
        Contract contract = contractDao.getById(contractId);
        Option option = optionDao.getById(optionId);
        Set<Option> options = contract.getOptions();
        if (!options.contains(option)) {
            throw new NotDataFoundException(Option.class.getName());
        }
        Set<Option> resultOptions = options.stream()
                .filter(option1 -> option1.getId()!=optionId)
                .collect(Collectors.toSet());
        contract.setOptions(resultOptions);
        contractDao.update(contract);
    }
}
