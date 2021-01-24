package com.javaschool.service;

import com.javaschool.dto.TariffDto;

import java.util.List;

public interface TariffService {

    List<TariffDto> getAll();

    void add(TariffDto tariff);

    void delete(long id);

    void update(long id, TariffDto tariff);

    TariffDto getById(long id);

    TariffDto getByName(String name);
}
