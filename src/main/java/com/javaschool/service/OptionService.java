package com.javaschool.service;

import com.javaschool.dao.OptionDao;
import com.javaschool.dto.OptionDto;
import com.javaschool.mapper.OptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OptionService {

    List<OptionDto> getAll();

    void add(OptionDto option);

    void delete(long id);

    void update(OptionDto option);

    OptionDto getById(long id);

    @Transactional
    void addMandatory(long idOption, long mandatoryOption);
}
