package com.javaschool.service;

import com.javaschool.model.Option;

import java.util.List;

public interface OptionService {

    List<Option> getAll();

    void add(Option option);

    void delete(long id);

    void update(Option option);

    Option getById(long id);
}
