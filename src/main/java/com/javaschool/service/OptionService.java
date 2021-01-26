package com.javaschool.service;

import com.javaschool.dto.OptionDto;
import com.javaschool.model.Option;

import java.util.List;
import java.util.Set;

public interface OptionService {

    List<OptionDto> getAll();

    void add(OptionDto option);

    void delete(long id);

    void update(OptionDto option);

    OptionDto getById(long id);

    void addMandatory(long idOption, long mandatoryOption);

    boolean deleteMandatoryOption(long idOption, long mandatoryOption);

    void addBannedOptionToDB(long idOption, long bannedOption);

    boolean deleteBannedOption(long idOption, long bannedOption);

    Set<OptionDto> splitSetMandatoryOptions(long optionId);

    Set<OptionDto> splitSetBannedOptions(long optionId);
}
