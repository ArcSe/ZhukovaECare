package com.javaschool.service;

import com.javaschool.model.Tariff;
import com.javaschool.repository.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TariffService {

    final
    TariffRepository tariffRepository;

    @Autowired
    public TariffService(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    public void save(Tariff tariff) {
        tariffRepository.save(tariff);
    }

    public List<Tariff> findAll() {
        return (List<Tariff>) tariffRepository.findAll();
    }

    public Tariff get(Long id) {
        return tariffRepository.findById(id).get();
    }

    public void delete(Long id) {
        tariffRepository.deleteById(id);
    }
}
