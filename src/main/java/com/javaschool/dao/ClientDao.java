package com.javaschool.dao;

import com.javaschool.model.Client;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDao extends AbstractDao<Client>{
    Client getByEmail(String email);

    void save(Client client);

    List<Client> getByName(String name);

    Client getByPassport(String passport);

    List<Client> getByPhone(String name);
}
