package com.javaschool.service.ipml;

import com.javaschool.dao.impl.ContractDaoImpl;
import com.javaschool.dao.impl.OptionDaoImpl;
import com.javaschool.dto.ContractDto;
import com.javaschool.exception.notFound.ExamplesNotFoundException;
import com.javaschool.exception.notFound.NotDataFoundException;
import com.javaschool.mapper.ContractMapper;
import com.javaschool.mapper.OptionMapper;
import com.javaschool.model.Contract;
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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

import static org.junit.jupiter.api.Assertions.*;

class ContractServiceImplTest {
    @TestConfiguration
    static class ContractServiceTestConfiguration {
        @Bean
        public ContractServiceImpl userService() {
            return new ContractServiceImpl();
        }
    }

    @InjectMocks
    ContractServiceImpl contractService;

    @Mock
    private ContractDaoImpl contractDao;

    @Mock
    private ContractMapper contractMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getById() {
        Contract contract = new Contract("+7 (000) 12 34", false, false, null, null, null);
        ContractDto contractDto = new ContractDto("+7 (000) 12 34", false, false, null, 0, null);

        Mockito.when(contractDao.getById(1)).thenReturn(contract);
        Mockito.when(contractMapper.toDto(contract)).thenReturn(contractDto);

        try {
            Assert.assertEquals(contractDto, contractService.getById(1));
        } catch (ExamplesNotFoundException e) {
            Assert.fail();
        }
    }

    @Test
    void generatePhoneNumberSuccessful() {
        Mockito.when(contractDao.generateNumber()).thenReturn(1234);
        try {
            Assert.assertEquals("+7 (000) 12 34", contractService.generatePhoneNumber());
        } catch (DataFormatException e) {
            Assert.fail();
        }
    }

    @Test
    void generatePhoneNumberFail() {
        Mockito.when(contractDao.generateNumber()).thenReturn(123456789);
        String message = null;
        try {
            contractService.generatePhoneNumber();
        } catch (DataFormatException e) {
            message = "DataFormatException";
        }
        Assert.assertEquals("DataFormatException", message);
    }


    @Test
    void lockedContract() {
        Contract contract = new Contract("+7 (000) 12 34", false, false, null, null, null);
        ContractDto contractDto = new ContractDto("+7 (000) 12 34", false, false, null, 0, null);

        Mockito.when(contractDao.getById(1)).thenReturn(contract);
        try {
            contractService.lockedContract(1);
        } catch (ExamplesNotFoundException e) {
            Assert.fail();
        }
        Assert.assertTrue(contract.isLocked());

    }

    @Test
    void lockedContractFail() {
        Contract contract = new Contract("+7 (000) 12 34", false, true, null, null, null);
        ContractDto contractDto = new ContractDto("+7 (000) 12 34", false, false, null, 0, null);

        Mockito.when(contractDao.getById(1)).thenReturn(contract);
        try {
            contractService.lockedContract(1);
        } catch (ExamplesNotFoundException e) {
            Assert.fail();
        }
        Assert.assertFalse(contract.isLocked());

    }


    @Test
    void lockedContractByAdmin() {
        Contract contract = new Contract("+7 (000) 12 34", false, false, null, null, null);
        ContractDto contractDto = new ContractDto("+7 (000) 12 34", false, false, null, 0, null);

        Mockito.when(contractDao.getById(1)).thenReturn(contract);
        try {
            contractService.lockedContractByAdmin(1);
        } catch (ExamplesNotFoundException e) {
            Assert.fail();
        }
        Assert.assertTrue(contract.isLocked());

    }
}