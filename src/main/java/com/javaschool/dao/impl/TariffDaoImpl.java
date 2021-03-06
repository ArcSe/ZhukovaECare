package com.javaschool.dao.impl;

import com.javaschool.dao.TariffDao;
import com.javaschool.model.Client;
import com.javaschool.model.Option;
import com.javaschool.model.Tariff;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Override
    public List<Tariff> getLast(int count) {
        List<Tariff> sortList = em.createQuery("select c from   Tariff  c  order by c.id desc ").getResultList();
        return sortList.stream().limit(count).collect(Collectors.toList());
    }

    @Override
    public void delete(long id) {
        Tariff tariff = getById(id);
        if(tariff.getContract().isEmpty()){
            super.delete(id);
        }
        else {
            em.createQuery("update Tariff t set t.deleted=true where t.id=?1").setParameter(1,id).executeUpdate();
        }
    }
}
