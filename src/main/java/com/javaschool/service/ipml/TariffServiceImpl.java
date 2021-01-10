package com.javaschool.service.ipml;

import com.javaschool.dao.TariffDao;
import com.javaschool.dto.TariffDto;
import com.javaschool.mapper.TariffMapper;
import com.javaschool.model.Option;
import com.javaschool.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TariffServiceImpl implements TariffService {
    
    private final TariffDao tariffDao;
    private final TariffMapper tariffMapper;


    @Autowired
    public TariffServiceImpl(TariffDao tariffDao, TariffMapper tariffMapper) {
        this.tariffDao = tariffDao;
        this.tariffMapper = tariffMapper;
    }


    @Override
    public List<TariffDto> getAll() {
        return tariffDao.getAll().stream().map(tariffMapper::toDto).collect(Collectors.toList());
    }


    @Override
    public void add(TariffDto tariff) {
        Set<Option> tariffDto = tariffMapper.toEntity(tariff).getOptions();
       Option option = tariffDto.stream().iterator().next();
        System.out.println(option.getId() + " " + option.getName() + " " + option.getPrice() + " " + option.getServiceCost());
        tariffDao.add(tariffMapper.toEntity(tariff));
    }

    @Override
    public void delete(long id) {
        tariffDao.delete(id);
    }

    @Override
    public void update(TariffDto tariff) {
        tariffDao.update(tariffMapper.toEntity(tariff));
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
}
