package com.javaschool.service;

import com.javaschool.dto.ClientDto;
import com.javaschool.exception.notFound.ExamplesNotFoundException;
import com.javaschool.exception.notFound.NotDataFoundException;
import com.javaschool.model.Client;
import com.javaschool.model.User;

import javax.transaction.Transactional;
import java.util.List;

public interface ClientService {
    List<ClientDto> getAll();

    boolean add(ClientDto option);

    void delete(long id);

    void update(ClientDto option);

    ClientDto getById(long id) throws ExamplesNotFoundException;

    ClientDto getClientDtoForUserProfile(User user);

    @Transactional
    void save(Client client);

    void addContract(long clientId, long contractId) throws ExamplesNotFoundException, NotDataFoundException;

    void deleteContracts(long clientId, long contractId) throws ExamplesNotFoundException;

    List<ClientDto> getAllByQuery(String name);
}
