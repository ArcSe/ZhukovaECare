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
        Set<Option> mandatoryOption;
        if(!Objects.isNull(option.getMandatoryOptions())) {
            mandatoryOption = option.getMandatoryOptions();
        }
        else {
            mandatoryOption = new HashSet<>();
        }
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

    @Override
    public void addBannedOption(long idOption, long bannedOptionId) {
        Option option = optionDao.getById(idOption);
        Option bannedOption = optionDao.getById(bannedOptionId);
        Set<Option> bannedOptions;
        if(!Objects.isNull(option.getBannedOptions())) {
            bannedOptions = option.getBannedOptions();
        }
        else {
            bannedOptions = new HashSet<>();
        }
        bannedOptions.add(bannedOption);
        option.setBannedOptions(bannedOptions);
        optionDao.update(option);

    }

    @Override
    public boolean deleteBannedOption(long idOption, long bannedOption) {
        Option optionDB = optionDao.getById(idOption);
        Option optionBannedDB = optionDao.getById(bannedOption);
        Set<Option> setBannedOptions = optionDB.getBannedOptions();
        if (!setBannedOptions.contains(optionBannedDB)) {
            return false;
        }
        Set<Option> options = optionDB.getBannedOptions().stream()
                .filter(option -> option.getId()!=bannedOption)
                .collect(Collectors.toSet());
        optionDB.setBannedOptions(options);
        optionDao.update(optionDB);
        return true;
    }

    @Override
    public Set<OptionDto> splitSetMandatoryOptions(long optionId) {
        Option optionDB = optionDao.getById(optionId);
        Set<Option> options = optionDao.getAll().stream().filter(o->o.getId()!=optionId).collect(Collectors.toSet());
        Set<Option> bannedOption = optionDB.getBannedOptions();
        options.removeAll(bannedOption);
        return options.stream().map(optionMapper::toDto).collect(Collectors.toSet());
    }

    @Override
    public Set<OptionDto> splitSetBannedOptions(long optionId) {
        Option optionDB = optionDao.getById(optionId);
        Set<Option> options = optionDao.getAll().stream().filter(o->o.getId()!=optionId).collect(Collectors.toSet());
        Set<Option> mandatoryOption = optionDB.getMandatoryOptions();
        options.removeAll(mandatoryOption);
        return options.stream().map(optionMapper::toDto).collect(Collectors.toSet());
    }

}
