package com.javaschool.service.ipml;

import com.javaschool.dao.ClientDao;
import com.javaschool.dao.ContractDao;
import com.javaschool.dto.ClientDto;
import com.javaschool.mapper.ClientMapper;
import com.javaschool.model.Client;
import com.javaschool.model.Contract;
import com.javaschool.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    
    private final ClientDao clientDao;
    private final ClientMapper clientMapper;
    private final ContractDao contractDao;

    @Autowired
    public ClientServiceImpl(ClientDao clientDao, ClientMapper clientMapper, ContractDao contractDao) {
        this.clientDao = clientDao;
        this.clientMapper = clientMapper;
        this.contractDao = contractDao;
    }


    @Override
    public List<ClientDto> getAll() {
        return clientDao.getAll().stream().map(clientMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(ClientDto client) {
        clientDao.add(clientMapper.toEntity(client));
    }

    @Override
    public void delete(long id) {
        clientDao.delete(id);
    }

    @Override
    public void update(ClientDto client) {
        clientDao.update(clientMapper.toEntity(client));
    }

    @Override
    public ClientDto getById(long id) {
        return clientMapper.toDto(clientDao.getById(id));
    }

    @Override
    public Client getByClientEmail(String email) {
        return clientDao.getByEmail(email);
    }

    @Override
    public void save(Client client) {
        clientDao.save(client);
    }

    @Override
    public void addContract(long clientId, long contractId) {
        Client client = clientDao.getById(clientId);
        Set<Contract> contracts;
        if(!Objects.isNull(client.getContracts())) {
            contracts = client.getContracts();
        }
        else {
            contracts = new HashSet<>();
        }
        contracts.add(contractDao.getById(contractId));
        client.setContracts(contracts);
        clientDao.update(client);
    }

    @Override
    public void deleteContracts(long clientId, long contractId) {
        Client client = clientDao.getById(clientId);
        Contract contract = contractDao.getById(contractId);
        Set<Contract> contracts = client.getContracts();
        if (!contracts.contains(contract)) {
            //todo: Exception EntityNotFound
        }
        client.setContracts(contracts);
        clientDao.update(client);
    }

    @Override
    public List<ClientDto> getAllByQuery(String name) {
        if(name.equals("")){
            return getAll();
        }
        else {
            return clientDao.getByName(name).stream().map(clientMapper::toDto).collect(Collectors.toList());
        }
    }
}
