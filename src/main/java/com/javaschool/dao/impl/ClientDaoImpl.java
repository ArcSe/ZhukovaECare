package com.javaschool.dao.impl;

import com.javaschool.dao.ClientDao;
import com.javaschool.model.Client;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDaoImpl extends AbstractJpaDaoImpl<Client> implements ClientDao {
    @Override
    public void add(Client o) {
        super.add(o);
        System.out.println("DAO" + o.getContracts().iterator().next().getNumber());
    }
}
