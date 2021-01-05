package com.javaschool.service.ipml;

import com.javaschool.dao.ContractDao;
import com.javaschool.dto.ContractDto;
import com.javaschool.mapper.ContractMapper;
import com.javaschool.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService {
    private final ContractDao contractDao;
    private final ContractMapper contractMapper;


    @Autowired
    public ContractServiceImpl(ContractDao contractDao, ContractMapper contractMapper) {
        this.contractDao = contractDao;
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
}
