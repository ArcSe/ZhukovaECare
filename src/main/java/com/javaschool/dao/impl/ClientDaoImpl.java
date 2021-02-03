package com.javaschool.dao.impl;

import com.javaschool.dao.ClientDao;
import com.javaschool.model.Client;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ClientDaoImpl extends AbstractJpaDaoImpl<Client> implements ClientDao {

    @Override
    public void save(Client client) {
        em.persist(client);
    }

    @Override
    public List<Client> getByName(String name) {
        TypedQuery<Client> query = em.createQuery("Select u from Client u " + "where (?1 is null or upper(u.name)"
                + " like concat('%', upper(?1), '%')) ", Client.class);
        query.setParameter(1, name);
        List<Client> foundUser=null;
        try {
            foundUser = query.getResultList();
        }
        catch (NoResultException e){
            //todo!!!Change to log!!!
            System.out.println(e.getCause());
        }
        return foundUser;
    }

    @Override
    public Client getByPassport(String passport) {
        TypedQuery<Client> query = em.createQuery("Select u from Client u where u.passport = ?1", Client.class);
        query.setParameter(1, passport);
        return (Client) query.getSingleResult();
    }

    @Override
    public List<Client> getByPhone(String phone) {
        TypedQuery<Client> query = em.createQuery("Select c from Client c " + "join Contract cont on c.id=cont.client.id where (?1 is null or upper(cont.number)"
                + " like concat('%', upper(?1), '%')) ", Client.class);
        query.setParameter(1, phone);
        List<Client> foundUser=null;
        try {
            foundUser = query.getResultList();
        }
        catch (NoResultException e){
            //todo!!!Change to log!!!
            System.out.println(e.getCause());
        }
        return foundUser;
    }

    @Override
    public Client getByEmail(String email) {
        return em.find(Client.class, email);
    }
}
