package com.javaschool.service;

import com.javaschool.dao.OptionDao;
import com.javaschool.model.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Option> getOptions() {
        return optionDao.getAll();
    }
}
