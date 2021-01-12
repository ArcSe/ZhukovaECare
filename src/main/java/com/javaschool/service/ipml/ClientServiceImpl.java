package com.javaschool.service.ipml;

import com.javaschool.dao.ClientDao;
import com.javaschool.dto.ClientDto;
import com.javaschool.mapper.ClientMapper;
import com.javaschool.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    
    private final ClientDao clientDao;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientServiceImpl(ClientDao clientDao, ClientMapper clientMapper) {
        this.clientDao = clientDao;
        this.clientMapper = clientMapper;
    }


    @Override
    public List<ClientDto> getAll() {
        return clientDao.getAll().stream().map(clientMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(ClientDto client) {
        clientDao.add(clientMapper.toEntity(client));
        System.out.println("Service" + clientMapper.toEntity(client).getContracts().iterator().next().getNumber());
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
}
