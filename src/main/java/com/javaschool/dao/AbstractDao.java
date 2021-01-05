package com.javaschool.dao;

import com.javaschool.model.AbstractModel;
import com.javaschool.model.Option;

import java.io.Serializable;
import java.util.List;

public interface AbstractDao<T extends AbstractModel> {

    List<T> getAll();

    void add(T o);

    T getById(long id);

    void delete(long id);

    void update(T o);
}
