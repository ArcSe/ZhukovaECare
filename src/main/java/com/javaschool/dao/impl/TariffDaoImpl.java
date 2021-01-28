package com.javaschool.dao.impl;

import com.javaschool.dao.TariffDao;
import com.javaschool.model.Client;
import com.javaschool.model.Option;
import com.javaschool.model.Tariff;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TariffDaoImpl extends AbstractJpaDaoImpl<Tariff> implements TariffDao {

    @Override
    public List<Tariff> getAllNotDeleted() {
        return em.createQuery("select c from   Tariff  c where c.deleted=false ").getResultList();
    }

    @Override
    public Tariff getByName(String name) {
        return em.find(Tariff.class, name);
    }
}
