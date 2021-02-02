package com.javaschool.dao;

import com.javaschool.model.Option;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionDao extends AbstractDao<Option>{
    public List<Option> getAllNotDeleted();

    public Option getByName(String name);
}
