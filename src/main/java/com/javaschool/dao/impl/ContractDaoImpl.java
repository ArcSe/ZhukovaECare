package com.javaschool.dao.impl;

import com.javaschool.dao.ContractDao;
import com.javaschool.model.Client;
import com.javaschool.model.Contract;
import com.javaschool.model.Counter;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.zip.DataFormatException;

@Repository
public class ContractDaoImpl extends AbstractJpaDaoImpl<Contract> implements ContractDao {

    @Override
    public int generateNumber() {
        TypedQuery<Integer> querySelect = em.createQuery("SELECT c.value from Counter c", Integer.class);
        int value =  querySelect.getSingleResult();;
        return value;
    }

    @Override
    public void incrementGenerator(){
        Query query = em.createNativeQuery("UPDATE Counter c SET c.value = c.value + 1" +
                " WHERE c.name = :name");
        query.setParameter("name", "contract");
        query.executeUpdate();
    }



}
