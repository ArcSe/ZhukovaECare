package com.javaschool.mapper;

import com.javaschool.dto.ClientDto;
import com.javaschool.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper extends AbstractMapper<Client, ClientDto> {
    @Autowired
    ClientMapper() {
        super(Client.class, ClientDto.class);
    }
}
