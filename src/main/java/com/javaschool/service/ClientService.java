package com.javaschool.service;

import com.javaschool.dto.ClientDto;

import java.util.List;

public interface ClientService {
    List<ClientDto> getAll();

    void add(ClientDto option);

    void delete(long id);

    void update(ClientDto option);

    ClientDto getById(long id);
}
