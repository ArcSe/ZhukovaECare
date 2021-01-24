package com.javaschool.service.ipml;

import com.javaschool.dao.OptionDao;
import com.javaschool.dto.OptionDto;
import com.javaschool.mapper.OptionMapper;
import com.javaschool.model.Option;
import com.javaschool.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@EnableTransactionManagement
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

    @Transactional
    @Override
    public void addMandatory(long optionId, long idMandatoryOption) {
        Option option = optionDao.getById(optionId);
        System.out.println(option);
        Set<Option> mandatoryOption;
        if(!Objects.isNull(option.getMandatoryOptions())) {
            mandatoryOption = option.getMandatoryOptions();
        }
        else {
            mandatoryOption = new HashSet<>();
        }
        System.out.println(optionDao.getById(idMandatoryOption));
        mandatoryOption.add(optionDao.getById(idMandatoryOption));
        option.setMandatoryOptions(mandatoryOption);
        optionDao.update(option);
    }

    @Override
    public boolean deleteMandatoryOption(long idOption, long mandatoryOption) {
        Option optionDB = optionDao.getById(idOption);
        Option optionMandatoryDB = optionDao.getById(mandatoryOption);
        Set<Option> setMandatoryOptions = optionDB.getMandatoryOptions();
        if (!setMandatoryOptions.contains(optionMandatoryDB)) {
            return false;
        }
        Set<Option> options = optionDB.getMandatoryOptions().stream()
                .filter(option -> option.getId()!=mandatoryOption)
                .collect(Collectors.toSet());
        optionDB.setMandatoryOptions(options);
        optionDao.update(optionDB);
        return true;
    }
}
