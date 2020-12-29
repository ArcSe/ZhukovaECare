package com.javaschool.service;

import com.javaschool.model.Option;
import com.javaschool.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionsService {
    final
    OptionRepository optionRepository;

    @Autowired
    public OptionsService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public void save(Option option) {
        optionRepository.save(option);
    }

    public List<Option> findAll() {
        return (List<Option>) optionRepository.findAll();
    }

    public Option get(Long id) {
        return optionRepository.findById(id).get();
    }

    public void delete(Long id) {
        optionRepository.deleteById(id);
    }
}
