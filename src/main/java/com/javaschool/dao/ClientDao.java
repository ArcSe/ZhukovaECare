package com.javaschool.dao;

import com.javaschool.model.Client;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ClientDao extends AbstractDao<Client>{
    Client getByEmail(String email);

    void save(Client client);

    List<Client> getByName(String name);
}
