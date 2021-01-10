package com.javaschool.dao;

import com.javaschool.model.Client;
import com.javaschool.model.Option;
import com.javaschool.model.Tariff;

import java.util.List;

public interface TariffDao extends AbstractDao<Tariff>{

    Tariff getByName(String name);
}
