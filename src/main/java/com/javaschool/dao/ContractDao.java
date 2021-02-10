package com.javaschool.dao;

import com.javaschool.model.Contract;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractDao extends AbstractDao<Contract>{

    int generateNumber();

    void incrementGenerator();
}
