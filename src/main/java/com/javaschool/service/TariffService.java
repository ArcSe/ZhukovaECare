package com.javaschool.service;

import com.javaschool.dto.TariffDto;
import com.javaschool.exception.notFound.BadValueException;
import com.javaschool.exception.notFound.NotDataFoundException;
import com.javaschool.exception.notFound.ExamplesNotFoundException;

import java.util.List;

public interface TariffService {

    List<TariffDto> getAll() throws NotDataFoundException;

    boolean add(TariffDto tariff) throws ExamplesNotFoundException;

    void delete(long id) throws ExamplesNotFoundException;

    void update( TariffDto tariff) throws ExamplesNotFoundException;

    TariffDto getById(long id) throws ExamplesNotFoundException;

    TariffDto getByName(String name) throws ExamplesNotFoundException;

    void removeOption(long optionId, long tariffId) throws ExamplesNotFoundException;

    void addOption(Long optionId, Long tariffId) throws BadValueException, ExamplesNotFoundException, Exception;
}
