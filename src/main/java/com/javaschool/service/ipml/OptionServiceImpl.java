package com.javaschool.service.ipml;

import com.javaschool.dao.OptionDao;
import com.javaschool.dto.OptionDto;
import com.javaschool.mapper.OptionMapper;
import com.javaschool.model.Option;
import com.javaschool.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OptionServiceImpl implements OptionService {

    private final OptionDao optionDao;
    private final OptionMapper optionMapper;

    @Autowired
    public OptionServiceImpl(OptionDao optionDao, OptionMapper optionMapper) {
        this.optionDao = optionDao;
        this.optionMapper = optionMapper;
    }


    @Override
    public List<OptionDto> getAll() {
        return optionDao.getAll().stream().map(optionMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void add(OptionDto option) {
        optionDao.add(optionMapper.toEntity(option));
    }

    @Override
    public void delete(long id) {
        optionDao.delete(id);
    }

    @Override
    public void update(OptionDto option) {
        optionDao.update(optionMapper.toEntity(option));
    }

    @Override
    public OptionDto getById(long id) {
        return optionMapper.toDto(optionDao.getById(id));
    }
}
