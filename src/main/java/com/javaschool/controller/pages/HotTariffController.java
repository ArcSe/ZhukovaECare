package com.javaschool.controller.pages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.dto.TariffDto;
import com.javaschool.exception.notFound.NotDataFoundException;
import com.javaschool.model.Tariff;
import com.javaschool.service.OptionService;
import com.javaschool.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HotTariffController {

    private final TariffService tariffService;

    @Autowired
    public HotTariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @GetMapping(value = "/hot", produces = "application/json")
    public List<TariffDto> getTariffsRest() throws JsonProcessingException, NotDataFoundException {
        return tariffService.getAll();
    }
}
