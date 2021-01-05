package com.javaschool.mapper;

import com.javaschool.dto.OptionDto;
import com.javaschool.dto.TariffDto;
import com.javaschool.model.Option;
import com.javaschool.model.Tariff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TariffMapper extends AbstractMapper<Tariff, TariffDto>{

    @Autowired
    TariffMapper() {
        super(Tariff.class, TariffDto.class);
    }
}
