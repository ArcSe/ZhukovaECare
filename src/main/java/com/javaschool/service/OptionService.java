package com.javaschool.service;

import com.javaschool.dto.OptionDto;
import com.javaschool.exception.notFound.ExamplesNotFoundException;
import com.javaschool.exception.notFound.NotDataFoundException;

import java.util.List;
import java.util.Set;

public interface OptionService {

    List<OptionDto> getAll() throws NotDataFoundException;

    boolean add(OptionDto option) throws ExamplesNotFoundException;

    void delete(long id) throws ExamplesNotFoundException;

    void update(OptionDto option) throws ExamplesNotFoundException;

    OptionDto getById(long id) throws ExamplesNotFoundException;

    OptionDto getByName(String name) throws ExamplesNotFoundException;

    void addMandatory(long idOption, long mandatoryOption) throws ExamplesNotFoundException;

    boolean deleteMandatoryOption(long idOption, long mandatoryOption) throws ExamplesNotFoundException;

    Set<OptionDto> splitSetMandatoryOptionsForTariff(long optionId);

    void addBannedOptionToDB(long idOption, long bannedOption) throws ExamplesNotFoundException;

    boolean deleteBannedOption(long idOption, long bannedOption);

    Set<OptionDto> splitSetMandatoryOptions(long optionId);

    Set<OptionDto> splitSetBannedOptions(long optionId);
}
