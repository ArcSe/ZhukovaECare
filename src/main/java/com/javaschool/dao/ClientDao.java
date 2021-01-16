package com.javaschool.dao;

import com.javaschool.model.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDao extends AbstractDao<Client>{
    Client getByEmail(String email);

    void save(Client client);
}
