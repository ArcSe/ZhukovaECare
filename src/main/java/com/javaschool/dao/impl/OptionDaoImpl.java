package com.javaschool.dao.impl;

import com.javaschool.dao.OptionDao;
import com.javaschool.model.Option;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OptionDaoImpl extends AbstractJpaDaoImpl<Option> implements OptionDao {
}
