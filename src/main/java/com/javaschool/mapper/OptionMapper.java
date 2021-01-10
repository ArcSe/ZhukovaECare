package com.javaschool.mapper;

import com.javaschool.dao.OptionDao;
import com.javaschool.dao.TariffDao;
import com.javaschool.dto.OptionDto;
import com.javaschool.model.Option;
import com.javaschool.model.Tariff;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
public class OptionMapper extends AbstractMapper<Option, OptionDto>{

    @Autowired
    OptionMapper() {
        super(Option.class, OptionDto.class);
    }
}
