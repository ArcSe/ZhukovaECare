package com.javaschool.dao;

import com.javaschool.model.Client;
import com.javaschool.model.Option;
import com.javaschool.model.Tariff;

import java.util.List;

public interface TariffDao extends AbstractDao<Tariff>{

    List<Tariff> getAllNotDeleted();

    Tariff getByName(String name);

    List<Tariff> getLast(int count);
}
