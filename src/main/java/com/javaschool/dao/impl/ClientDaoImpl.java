package com.javaschool.dao.impl;

import com.javaschool.dao.ClientDao;
import com.javaschool.model.Client;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDaoImpl extends AbstractJpaDaoImpl<Client> implements ClientDao {

    @Override
    public void save(Client client) {
        System.out.println(client.getEmail() + " " + client.getPassword() + " " + client.isActive());
        em.persist(client);
    }

    @Override
    public Client getByEmail(String email) {
        return em.find(Client.class, email);
    }
}
