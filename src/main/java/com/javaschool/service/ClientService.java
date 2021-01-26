package com.javaschool.service;

import com.javaschool.dto.ClientDto;
import com.javaschool.model.Client;

import javax.transaction.Transactional;
import java.util.List;

public interface ClientService {
    List<ClientDto> getAll();

    void add(ClientDto option);

    void delete(long id);

    void update(ClientDto option);

    ClientDto getById(long id);

    Client getByClientEmail(String email);

    @Transactional
    void save(Client client);

    void addContract(long clientId, long contractId);

    void deleteContracts(long clientId, long contractId);
}
