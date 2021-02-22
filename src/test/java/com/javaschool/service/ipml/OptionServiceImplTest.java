package com.javaschool.service.ipml;

import com.javaschool.dao.impl.OptionDaoImpl;
import com.javaschool.exception.notFound.ExamplesNotFoundException;
import com.javaschool.exception.notFound.NotDataFoundException;
import com.javaschool.mapper.OptionMapper;
import com.javaschool.model.Option;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@RunWith(SpringRunner.class)
class OptionServiceImplTest {

    @TestConfiguration
    static class OptionServiceTestConfiguration {

        @Bean
        public OptionServiceImpl userService() {
            return new OptionServiceImpl();
        }
    }

    @InjectMocks
    OptionServiceImpl optionService;

    @Mock
    private OptionDaoImpl optionDao;

    @Mock
    private OptionMapper optionMapper;


    @BeforeEach
    void setUp() {
        //enable mocks
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllFail(){
        Mockito.when(optionDao.getAll()).thenReturn(null);
        String message = null;
        try {
            optionService.getAll();
        }
        catch (NotDataFoundException e){
            message = "NotDataFoundException";
        }
        Assert.assertEquals("NotDataFoundException", message );
    }

    @Test
    void getAllSuccessful() throws ExamplesNotFoundException {

        String message = null;
        Option option = new Option("option", 100, 100,false, null, null, new HashSet<>(), new HashSet<>());
        List<Option> options = new ArrayList<>();
        options.add(option);
        Mockito.when(optionDao.getAll()).thenReturn(options);
        try {
            optionService.getAll();
            message = "All options were gotten";
        }
        catch (NotDataFoundException e){
            message = "NotDataFoundException";
        }
        Assert.assertEquals("All options were gotten", message);
    }

    @Test
    void addMandatory() {
        Option option1 = new Option("option1", 100, 100,false, null, null, new HashSet<>(), new HashSet<>());
        Option option2 = new Option("option2", 100, 100,false, null, null, new HashSet<>(), new HashSet<>());
        Option option3 = new Option("option3", 100, 100,false, null, null, new HashSet<>(), new HashSet<>());
        Option option4 = new Option("option4", 100, 100,false, null, null, new HashSet<>(), new HashSet<>());

        HashSet<Option> mandatoryOptions = new HashSet<>();
        mandatoryOptions.add(option1);
        option2.setMandatoryOptions(mandatoryOptions);

    }

    @Test
    void deleteMandatoryOption() {
    }

    @Test
    void addBannedOptionToDB() {
    }

    @Test
    void addBannedOption() {
    }

    @Test
    void deleteBannedOption() {
    }

    @Test
    void splitSetMandatoryOptions() {
    }

    @Test
    void splitSetBannedOptions() {
    }


}