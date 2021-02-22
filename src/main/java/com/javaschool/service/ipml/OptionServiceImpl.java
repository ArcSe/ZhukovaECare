package com.javaschool.service.ipml;

import com.javaschool.dao.OptionDao;
import com.javaschool.dto.OptionDto;
import com.javaschool.exception.notFound.ExamplesNotFoundException;
import com.javaschool.exception.notFound.NotDataFoundException;
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

    private  OptionDao optionDao;
    private  OptionMapper optionMapper;

    @Autowired
    public OptionServiceImpl(OptionDao optionDao, OptionMapper optionMapper) {
        this.optionDao = optionDao;
        this.optionMapper = optionMapper;
    }

    public OptionServiceImpl() {
    }

    @Override

    public List<OptionDto> getAll() throws NotDataFoundException {
        if(Objects.isNull(optionDao.getAll())){
            throw new NotDataFoundException(Option.class.getName());
        }
        return optionDao.getAllNotDeleted().stream().map(optionMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean add(OptionDto option) throws ExamplesNotFoundException {
        OptionDto optionFromDb = getByName(option.getName());

        if (optionFromDb != null) {
            return false;
        }
        optionDao.add(optionMapper.toEntity(option));

        return true;
    }

    @Override
    @Transactional
    public void delete(long id) throws ExamplesNotFoundException {
        if(Objects.isNull(optionDao.getById(id))){
            throw new ExamplesNotFoundException(id);
        }
        optionDao.delete(id);
    }

    @Override
    @Transactional
    public void update(OptionDto option) throws ExamplesNotFoundException {
        optionDao.update(optionMapper.toEntity(option));
    }

    @Override
    public OptionDto getById(long id) throws ExamplesNotFoundException {
        if(Objects.isNull(optionDao.getById(id))){
            throw new ExamplesNotFoundException(id);
        }
        return optionMapper.toDto(optionDao.getById(id));
    }

    @Override
    public OptionDto getByName(String name) throws ExamplesNotFoundException {
        return optionMapper.toDto(optionDao.getByName(name));
    }

    @Transactional
    @Override
    public void addMandatory(long optionId, long idMandatoryOption) throws ExamplesNotFoundException {
        if(Objects.isNull(optionDao.getById(optionId))){
            throw new ExamplesNotFoundException(optionId);
        }
        if(Objects.isNull(optionDao.getById(idMandatoryOption))){
            throw new ExamplesNotFoundException(idMandatoryOption);
        }

        Option option = optionDao.getById(optionId);
        Option mandatoryOption = optionDao.getById(idMandatoryOption);


        Set<Option> mandatoryOptions;
        if(!Objects.isNull(option.getMandatoryOptions())) {
            mandatoryOptions = option.getMandatoryOptions();
        }
        else {
            mandatoryOptions = new HashSet<>();
        }

        mandatoryOptions.addAll(recursMandatoryOption(mandatoryOptions, mandatoryOption,option));
        option.setMandatoryOptions(mandatoryOptions);
        Set<Option> banOption = addBannedOption(optionId, idMandatoryOption).getBannedOptions();
        banOption.remove(mandatoryOption);
        option.getBannedOptions().addAll(banOption);
        optionDao.update(option);
    }

    private Set<Option> recursMandatoryOption(Set<Option> options, Option newOption, Option baseOption){
        options.add(newOption);
        Set<Option> mandatoryOptions;
        if(!Objects.isNull(newOption.getMandatoryOptions())) {
            mandatoryOptions = newOption.getMandatoryOptions();
        }
        else {
            mandatoryOptions = new HashSet<>();
        }
        mandatoryOptions.removeAll(options);
        if(mandatoryOptions.isEmpty() || newOption.getId()== baseOption.getId()){
            return options;
        }
        else {
            mandatoryOptions.stream().filter(option -> option.getId()!= baseOption.getId())
                    .map(option ->  options.addAll(recursMandatoryOption(mandatoryOptions, option, baseOption)))
                    .collect(Collectors.toSet());
        }

        return options;
    }

    private Set<Option> recursBannedOption(Set<Option> options, Option newOption){
        options.add(newOption);
        Set<Option> bannedOptions;
        if(!Objects.isNull(newOption.getBannedOptions())) {
            bannedOptions = newOption.getBannedOptions();
        }
        else {
            bannedOptions = new HashSet<>();
        }
        bannedOptions.removeAll(options);
        if(bannedOptions.isEmpty()){
            return options;
        }
        else {
 //           bannedOptions.forEach(option ->  options.addAll(recursMandatoryOption(bannedOptions, option, )));
        }

        return options;
    }

    @Override
    @Transactional
    public boolean deleteMandatoryOption(long idOption, long mandatoryOption) throws ExamplesNotFoundException {
        if(Objects.isNull(optionDao.getById(idOption))){
            throw new ExamplesNotFoundException(idOption);
        }
        if(Objects.isNull(optionDao.getById(mandatoryOption))){
            throw new ExamplesNotFoundException(mandatoryOption);
        }
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
    @Transactional
    public void addBannedOptionToDB(long idOption, long bannedOptionId) throws ExamplesNotFoundException {
        Option option = addBannedOption(idOption,bannedOptionId);
        optionDao.update(option);
    }


    @Transactional
    public Option addBannedOption(long idOption, long bannedOptionId) throws ExamplesNotFoundException {
        Option option = optionDao.getById(idOption);
        Option bannedOption = optionDao.getById(bannedOptionId);
        Set<Option> bannedOptions;
        if(!Objects.isNull(option.getBannedOptions())) {
            bannedOptions = option.getBannedOptions();
        }
        else {
            bannedOptions = new HashSet<>();
        }
        //bannedOptions.add(bannedOption);
        Set<Option> resultRecurs = recursBannedOption(bannedOptions, bannedOption).stream()
                .filter(option1 -> !option1.equals(option))
                .collect(Collectors.toSet());
        bannedOptions.addAll(resultRecurs);
        option.setBannedOptions(resultRecurs);
        return option;
    }

    @Override
    @Transactional
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
        Set<Option> options = optionDao.getAllNotDeleted().stream().filter(o->o.getId()!=optionId).collect(Collectors.toSet());
        Set<Option> bannedOption = optionDB.getBannedOptions();
        options.removeAll(bannedOption);
        return options.stream().map(optionMapper::toDto).collect(Collectors.toSet());
    }

    @Override
    public Set<OptionDto> splitSetBannedOptions(long optionId) {
        Option optionDB = optionDao.getById(optionId);
        Set<Option> options = optionDao.getAllNotDeleted().stream().filter(o->o.getId()!=optionId).collect(Collectors.toSet());
        Set<Option> mandatoryOption = optionDB.getMandatoryOptions();
        options.removeAll(mandatoryOption);
        return options.stream().map(optionMapper::toDto).collect(Collectors.toSet());
    }

}
