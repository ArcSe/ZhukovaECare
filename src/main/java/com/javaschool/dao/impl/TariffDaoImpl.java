package com.javaschool.dao.impl;

import com.javaschool.dao.TariffDao;
import com.javaschool.model.Client;
import com.javaschool.model.Tariff;
import org.springframework.stereotype.Repository;

@Repository
public class TariffDaoImpl extends AbstractJpaDaoImpl<Tariff> implements TariffDao {

    @Override
    public Tariff getByName(String name) {
        return em.find(Tariff.class, name);
    }
}
