package com.javaschool.dao.impl;

import com.javaschool.dao.TariffDao;
import com.javaschool.model.Client;
import com.javaschool.model.Option;
import com.javaschool.model.Tariff;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TariffDaoImpl extends AbstractJpaDaoImpl<Tariff> implements TariffDao {

    @Override
    public List<Tariff> getAllNotDeleted() {
        return em.createQuery("select c from   Tariff  c where c.deleted=false ").getResultList();
    }

    @Override
    public Tariff getByName(String name) {
        TypedQuery<Tariff> query = em.createQuery("Select u from Tariff u where  u.name=?1", Tariff.class);
        query.setParameter(1, name);
        Tariff foundtariff = null;
        try {
            foundtariff = query.getSingleResult();
        } catch (NoResultException e) {
            //todo!!!Change to log!!!
            System.out.println(e.getCause());
        }
        return foundtariff;
    }
}
