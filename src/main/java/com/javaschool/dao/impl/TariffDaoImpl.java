package com.javaschool.dao.impl;

import com.javaschool.dao.TariffDao;
import com.javaschool.model.Tariff;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class TariffDaoImpl extends AbstractJpaDaoImpl<Tariff> implements TariffDao {

}
