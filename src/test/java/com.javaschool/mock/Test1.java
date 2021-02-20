package com.javaschool.mock;

import com.javaschool.dao.TariffDao;
import com.javaschool.dto.TariffDto;
import com.javaschool.exception.notFound.ExamplesNotFoundException;
import com.javaschool.service.TariffService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class Test1 {

    @Mock
    private TariffDao tariffDao;

    @InjectMocks
    private TariffService tariffService;

    @BeforeEach
    void onSetUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void add(){
        TariffDto tariffDto = new TariffDto();
        boolean isTariffCreated = false;
        try {
            isTariffCreated = tariffService.add(tariffDto);
        } catch (ExamplesNotFoundException e) {
            e.printStackTrace();
        }
        assertTrue(isTariffCreated);

    }
}
