package com.javaschool.service;

import com.javaschool.dto.TariffDto;
import com.javaschool.exception.notFound.BadValueException;
import com.javaschool.exception.notFound.NotDataFoundException;
import com.javaschool.exception.notFound.TariffNotFoundException;

import java.util.List;

public interface TariffService {

    List<TariffDto> getAll() throws NotDataFoundException;

    void add(TariffDto tariff);

    void delete(long id) throws TariffNotFoundException;

    void update( TariffDto tariff) throws TariffNotFoundException;

    TariffDto getById(long id) throws TariffNotFoundException;

    TariffDto getByName(String name) throws TariffNotFoundException;

    void removeOption(long optionId, long tariffId) throws TariffNotFoundException;

    void addOption(Long optionId, Long tariffId) throws BadValueException, TariffNotFoundException;
}
