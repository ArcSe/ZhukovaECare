package com.javaschool.mapper;

import com.javaschool.dto.OptionDto;
import com.javaschool.model.Option;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OptionMapper extends AbstractMapper<Option, OptionDto>{

    @Autowired
    OptionMapper() {
        super(Option.class, OptionDto.class);
    }
}
