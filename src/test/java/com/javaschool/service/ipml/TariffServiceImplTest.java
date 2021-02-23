package com.javaschool.service.ipml;

import com.javaschool.dao.impl.OptionDaoImpl;
import com.javaschool.dao.impl.TariffDaoImpl;
import com.javaschool.dto.TariffDto;
import com.javaschool.exception.notFound.ExamplesNotFoundException;
import com.javaschool.exception.notFound.NotDataFoundException;
import com.javaschool.mapper.OptionMapper;
import com.javaschool.mapper.TariffMapper;
import com.javaschool.model.Tariff;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TariffServiceImplTest {
    @TestConfiguration
    static class TariffServiceTestConfiguration {

        @Bean
        public TariffServiceImpl userService() {
            return new TariffServiceImpl();
        }
    }

    @InjectMocks
    TariffServiceImpl tariffServiceImp;

    @Mock
    private TariffDaoImpl tariffDao;

    @Mock
    private TariffMapper tariffMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllSuccessful()  {
        Tariff tariff1 = new Tariff("tariff1", 0, 0, false, null, null);
        Tariff tariff2 = new Tariff("tariff2", 0, 0, true, null, null);
        Mockito.when(tariffDao.getAllNotDeleted()).thenReturn(new ArrayList<>(Arrays.asList(tariff1)));
        try {
            Assert.assertNotNull(tariffServiceImp.getAll());
        }
        catch (NotDataFoundException e){
            Assert.fail();
        }
    }

    @Test
    void getAllFail()  {
        Tariff tariff1 = new Tariff("tariff1", 0, 0, true, null, null);
        Tariff tariff2 = new Tariff("tariff2", 0, 0, true, null, null);
        Mockito.when(tariffDao.getAllNotDeleted()).thenReturn(null);
        String message = null;
        try {
            Assert.assertNotNull(tariffServiceImp.getAll());
        }
        catch (NotDataFoundException e){
            message = "NotDataFoundException";
        }
        Assert.assertEquals("NotDataFoundException", message);
    }

    @Test
    void addFail() {
        Tariff tariff1 = new Tariff("tariff1", 0, 0, true, null, null);
        TariffDto tariffDto = new TariffDto("tariff1", 0, 0, null);
        Mockito.when(tariffDao.getByName("tariff1")).thenReturn(tariff1);
        Mockito.when(tariffMapper.toEntity(tariffDto)).thenReturn(tariff1);

        try {
            Assert.assertFalse(tariffServiceImp.add(tariffDto));
        } catch (ExamplesNotFoundException e) {
            Assert.fail();
        }
    }

    @Test
    void addSuccessful() {
        Tariff tariff1 = new Tariff("tariff1", 0, 0, true, null, null);
        TariffDto tariffDto = new TariffDto("tariff1", 0, 0, null);
        Mockito.when(tariffDao.getByName("tariff1")).thenReturn(null);
        Mockito.when(tariffMapper.toEntity(tariffDto)).thenReturn(tariff1);

        try {
            Assert.assertTrue(tariffServiceImp.add(tariffDto));
        } catch (ExamplesNotFoundException e) {
            Assert.fail();
        }
    }

    @Test
    void getAllHotTariffs() {
    }
}