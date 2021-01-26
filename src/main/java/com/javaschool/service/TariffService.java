package com.javaschool.service;

import com.javaschool.dto.TariffDto;

import java.util.List;

public interface TariffService {

    List<TariffDto> getAll();

    void add(TariffDto tariff);

    void delete(long id);

    void update( TariffDto tariff);

    TariffDto getById(long id);

    TariffDto getByName(String name);

    void removeOption(long optionId, long tariffId);

    void addOption(Long optionId, Long tariffId);
}
