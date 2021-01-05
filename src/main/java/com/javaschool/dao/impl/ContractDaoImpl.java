package com.javaschool.dao.impl;

import com.javaschool.dao.ContractDao;
import com.javaschool.model.Contract;
import org.springframework.stereotype.Repository;

@Repository
public class ContractDaoImpl extends AbstractJpaDaoImpl<Contract> implements ContractDao {
}
