package com.javaschool.dao;

import com.javaschool.model.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public interface OptionDao {

    List<Option> getAll();

    void add(Option option);

    Option getById(long id);

    void delete(long id);

    void update(Option option);
}
