package com.javaschool.service;

import com.javaschool.dao.OptionDao;
import com.javaschool.model.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OptionServiceImpl implements OptionService{
    OptionDao optionDao;

    @Autowired
    public OptionServiceImpl(OptionDao optionDao) {
        this.optionDao = optionDao;
    }

    public void setOptionDao(OptionDao optionDao) {
        this.optionDao = optionDao;
    }

    @Override
    public List<Option> getAll() {
        return optionDao.getAll();
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
