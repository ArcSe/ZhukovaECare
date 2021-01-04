package com.javaschool.service;

import com.javaschool.dto.OptionDto;
import com.javaschool.model.Option;

import java.util.List;

public interface OptionService {

    List<OptionDto> getAll();

    void add(OptionDto option);

    void delete(long id);

    void update(OptionDto option);

    OptionDto getById(long id);
}
