package com.javaschool.service.ipml;

import com.javaschool.dao.OptionDao;
import com.javaschool.dto.OptionDto;
import com.javaschool.mapper.OptionMapper;
import com.javaschool.model.Option;
import com.javaschool.service.OptionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OptionServiceImpl implements OptionService {

    private final OptionDao optionDao;
    private final OptionMapper optionMapper;


    public OptionServiceImpl(OptionDao optionDao, OptionMapper optionMapper) {
        this.optionDao = optionDao;
        this.optionMapper = optionMapper;
    }


    @Override
    public List<OptionDto> getAll() {
        return optionDao.getAll().stream().map(optionMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void add(Option option) {
        optionDao.add(option);
    }

    @Override
    public void delete(long id) {
        optionDao.delete(id);
    }

    @Override
    public void update(Option option) {
        optionDao.update(option);
    }

    @Override
    public Option getById(long id) {
        return optionDao.getById(id);
    }
}
