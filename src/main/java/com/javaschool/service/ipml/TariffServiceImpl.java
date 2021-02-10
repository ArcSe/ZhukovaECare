package com.javaschool.service.ipml;

import com.javaschool.dao.OptionDao;
import com.javaschool.dao.TariffDao;
import com.javaschool.dto.OptionDto;
import com.javaschool.dto.TariffDto;
import com.javaschool.exception.notFound.BadValueException;
import com.javaschool.exception.notFound.NotDataFoundException;
import com.javaschool.exception.notFound.ExamplesNotFoundException;
import com.javaschool.mapper.TariffMapper;
import com.javaschool.model.Option;
import com.javaschool.model.Tariff;
import com.javaschool.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<TariffDto> getAll() throws NotDataFoundException {
        if(Objects.isNull(tariffDao.getAllNotDeleted())){
            throw new NotDataFoundException(Tariff.class.getName());
        }
        return tariffDao.getAllNotDeleted().stream().map(tariffMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public boolean add(TariffDto tariff) throws ExamplesNotFoundException {
        Tariff tariffFromDb = tariffDao.getByName(tariff.getName());

        if (tariffFromDb != null) {
            return false;
        }
        tariffDao.add(tariffMapper.toEntity(tariff));
        return true;
    }

    @Override
    public void delete(long id) throws ExamplesNotFoundException {
        if(Objects.isNull(tariffDao.getById(id))){
            throw new ExamplesNotFoundException(id);
        }
        tariffDao.delete(id);
    }

    @Override
    public void update( TariffDto tariffDto) throws ExamplesNotFoundException {
        if(Objects.isNull(tariffDto)){
            throw new ExamplesNotFoundException(tariffDto.getId());
        }
        Tariff tariff = tariffMapper.toEntity(tariffDto);
        tariffDao.update(tariff);
    }

    @Override
    public TariffDto getById(long id) throws ExamplesNotFoundException {
        if(Objects.isNull(tariffDao.getById(id))){
            throw new ExamplesNotFoundException(id);
        }
        TariffDto dto = tariffMapper.toDto(tariffDao.getById(id));
        return dto;
    }

    @Override
    public TariffDto getByName(String name) throws ExamplesNotFoundException {
        if(Objects.isNull(tariffDao.getByName(name))){
            throw new ExamplesNotFoundException(name);
        }
        TariffDto dto = tariffMapper.toDto(tariffDao.getByName(name));
        return dto;
    }

    @Override
    public void removeOption(long optionId, long tariffId) throws ExamplesNotFoundException {
        if(Objects.isNull(tariffDao.getById(tariffId))){
            throw new ExamplesNotFoundException(tariffId);
        }
        Tariff tariff = tariffDao.getById(tariffId);
        Set<Option> options = tariff.getOptions().stream()
                .filter(option -> option.getId()!=optionId)
                .collect(Collectors.toSet());
        tariff.setOptions(options);
        changeTariffPrice(tariff);
        changeTariffServiceCost(tariff);
        tariffDao.update(tariff);
    }

    //todo message about banned options
    @Override
    public void addOption(Long optionId, Long tariffId) throws Exception {
        if(Objects.isNull(tariffDao.getById(tariffId))){
            throw new ExamplesNotFoundException(tariffId);
        }
        Tariff tariff = tariffDao.getById(tariffId);
        Set<Option> options = tariff.getOptions();
        Option option = optionDao.getById(optionId);
        Set<Option> optionCheck = options.stream().filter(option1 -> !option1.getBannedOptions().contains(option))
                .collect(Collectors.toSet());
        if(optionCheck.size() != options.size()){
            throw new BadValueException();
        }
        Set<Option> mandatoryOptions = option.getMandatoryOptions();
        options.addAll(mandatoryOptions);
        options.add(option);
        tariff.setOptions(options);
        changeTariffPrice(tariff);
        changeTariffServiceCost(tariff);
        tariffDao.update(tariff);
    }

    private void changeTariffPrice(Tariff tariff){
        Set<Option> options = tariff.getOptions();
        int tariffPrice = 0;
        for (Option option: options) {
            tariffPrice+=option.getPrice();
        }
        tariff.setPrice(tariffPrice);
    }

    private void changeTariffServiceCost(Tariff tariff){
        Set<Option> options = tariff.getOptions();
        int tariffPrice = 0;
        for (Option option: options) {
            tariffPrice+=option.getServiceCost();
        }
        tariff.setServiceCost(tariffPrice);
    }
}
