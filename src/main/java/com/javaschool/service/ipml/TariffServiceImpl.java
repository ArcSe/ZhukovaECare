package com.javaschool.service.ipml;

import com.javaschool.dao.OptionDao;
import com.javaschool.dao.TariffDao;
import com.javaschool.dto.OptionDto;
import com.javaschool.dto.TariffDto;
import com.javaschool.exception.BadValueException;
import com.javaschool.mapper.TariffMapper;
import com.javaschool.model.Option;
import com.javaschool.model.Tariff;
import com.javaschool.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TariffServiceImpl implements TariffService {
    
    private final TariffDao tariffDao;
    private final TariffMapper tariffMapper;
    private final OptionDao optionDao;


    @Autowired
    public TariffServiceImpl(TariffDao tariffDao, TariffMapper tariffMapper, OptionDao optionDao) {
        this.tariffDao = tariffDao;
        this.tariffMapper = tariffMapper;
        this.optionDao = optionDao;
    }

    @Override
    public List<TariffDto> getAll() {
        return tariffDao.getAll().stream().map(tariffMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(TariffDto tariff) {
        tariffDao.add(tariffMapper.toEntity(tariff));
    }

    @Override
    public void delete(long id) {
        tariffDao.delete(id);
    }

    @Override
    public void update( TariffDto tariffDto) {
        Tariff tariff = tariffMapper.toEntity(tariffDto);
        tariffDao.update(tariff);
    }

    @Override
    public TariffDto getById(long id) {
        TariffDto dto = tariffMapper.toDto(tariffDao.getById(id));
        return dto;
    }

    @Override
    public TariffDto getByName(String name) {
        TariffDto dto = tariffMapper.toDto(tariffDao.getByName(name));
        return dto;
    }

    @Override
    public void removeOption(long optionId, long tariffId) {
        Tariff tariff = tariffDao.getById(tariffId);
        Set<Option> options = tariff.getOptions().stream()
                .filter(option -> option.getId()!=optionId)
                .collect(Collectors.toSet());
        tariff.setOptions(options);
        tariffDao.update(tariff);
    }

    //todo message about banned options
    @Override
    public void addOption(Long optionId, Long tariffId){
        Tariff tariff = tariffDao.getById(tariffId);
        Set<Option> options = tariff.getOptions();
        Option option = optionDao.getById(optionId);
        options.forEach(option1 -> {if(option1.getBannedOptions().contains(option)){
            try {
                throw new BadValueException();
            } catch (BadValueException e) {
                e.printStackTrace();
            }
        }});
        Set<Option> mandatoryOptions = option.getMandatoryOptions();
        options.addAll(mandatoryOptions);
        options.add(option);
        tariff.setOptions(options);
        tariffDao.update(tariff);
    }
}
