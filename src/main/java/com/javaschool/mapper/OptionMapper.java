package com.javaschool.mapper;

import com.javaschool.dao.OptionDao;
import com.javaschool.dto.OptionDto;
import com.javaschool.model.Option;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
public class OptionMapper extends AbstractMapper<Option, OptionDto>{

    private final ModelMapper mapper;
    private final OptionDao optionDao;

    @Autowired
    OptionMapper(ModelMapper mapper, OptionDao optionDao) {
        super(Option.class, OptionDto.class);
        this.mapper = mapper;
        this.optionDao = optionDao;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Option.class, OptionDto.class)
                .addMappings(m -> {m.skip(OptionDto::setMandatoryOptions); m.skip(OptionDto::setBannedOptions);})
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(OptionDto.class, Option.class)
                .addMappings(m -> {m.skip(Option::setMandatoryOptions); m.skip(Option::setBannedOptions);}).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(Option source, OptionDto destination) {
        if(!Objects.isNull(getSet(source))){
            Set<Long> optionsMandatoryId = new HashSet<>();
            source.getMandatoryOptions().forEach(o -> optionsMandatoryId.add(o.getId()));
            destination.setMandatoryOptions(optionsMandatoryId);
            Set<Long> optionsBannedId = new HashSet<>();
            source.getBannedOptions().forEach(o -> optionsBannedId.add(o.getId()));
            destination.setBannedOptions(optionsBannedId);
        }

    }

    private Set<Option> getSet(Option source) {
        return Objects.isNull(source) || Objects.isNull(source.getMandatoryOptions()) ? null : source.getMandatoryOptions();
    }

    @Override
    void mapSpecificFields(OptionDto source, Option destination) {
        Set<Option> options = new HashSet<>();
        if(!Objects.isNull(source.getMandatoryOptions())) {
            source.getMandatoryOptions().forEach(o -> options.add(optionDao.getById(o)));
            destination.setMandatoryOptions(options);
        }
        Set<Option> bannedOptions = new HashSet<>();
        if(!Objects.isNull(source.getBannedOptions())) {
            source.getBannedOptions().forEach(o -> bannedOptions.add(optionDao.getById(o)));
            destination.setBannedOptions(bannedOptions);
        }

    }
}
