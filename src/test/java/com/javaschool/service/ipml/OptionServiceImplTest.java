package com.javaschool.service.ipml;

import com.javaschool.dao.impl.OptionDaoImpl;
import com.javaschool.dto.OptionDto;
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

import java.util.*;


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
    OptionServiceImpl optionServiceImp;

    @Mock
    private OptionDaoImpl optionDao;

    @Mock
    private OptionMapper optionMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllFail(){
        Mockito.when(optionDao.getAll()).thenReturn(null);
        String message = null;
        try {
            optionServiceImp.getAll();
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
            optionServiceImp.getAll();
            message = "All options were gotten";
        }
        catch (NotDataFoundException e){
            message = "NotDataFoundException";
        }
        Assert.assertEquals("All options were gotten", message);
    }

    @Test
    void deleteMandatoryOptionSuccessful() throws ExamplesNotFoundException {
        Option option1 = new Option("option1", 100, 100, false, null, null,
                new HashSet<>(), new HashSet<>());
        Option option2 = new Option("option2", 100, 100, false, null, null,
                new HashSet<>(Arrays.asList(option1)), new HashSet<>());
        Option option3 = new Option("option3", 100, 100, false, null, null,
                new HashSet<>(Arrays.asList(option2)), new HashSet<>());
        Option option4 = new Option("option4", 100, 100, false, null, null,
                new HashSet<>(Arrays.asList(option3)), new HashSet<>());
        OptionDto optionDto1 = new OptionDto("option1", 100, 100, new HashMap<>(), new HashMap<>());
        Map<Long, String> option = new HashMap<>();
        option.put(optionDto1.getId(), optionDto1.getName());
        OptionDto optionDto2 = new OptionDto("option2", 100, 100, option, new HashMap<>());
        Map<Long, String> optionMan2 = new HashMap<>();
        optionMan2.put(optionDto2.getId(), optionDto2.getName());

        Mockito.when(optionDao.getById(4)).thenReturn(option4);
        Mockito.when(optionDao.getById(3)).thenReturn(option3);

        Assert.assertTrue(optionServiceImp.deleteMandatoryOption(4, 3));
    }

    @Test
    void deleteMandatoryOptionFail() throws ExamplesNotFoundException {
        Option option1 = new Option("option1", 100, 100, false, null, null,
                new HashSet<>(), new HashSet<>());
        Option option2 = new Option("option2", 100, 100, false, null, null,
                new HashSet<>(Arrays.asList(option1)), new HashSet<>());
        Option option3 = new Option("option3", 100, 100, false, null, null,
                new HashSet<>(Arrays.asList(option2)), new HashSet<>());
        Option option4 = new Option("option4", 100, 100, false, null, null,
                new HashSet<>(), new HashSet<>());
        OptionDto optionDto1 = new OptionDto("option1", 100, 100, new HashMap<>(), new HashMap<>());
        Map<Long, String> option = new HashMap<>();
        option.put(optionDto1.getId(), optionDto1.getName());
        OptionDto optionDto2 = new OptionDto("option2", 100, 100, option, new HashMap<>());
        Map<Long, String> optionMan2 = new HashMap<>();
        optionMan2.put(optionDto2.getId(), optionDto2.getName());

        Mockito.when(optionDao.getById(4)).thenReturn(option4);
        Mockito.when(optionDao.getById(3)).thenReturn(option3);

        Assert.assertFalse(optionServiceImp.deleteMandatoryOption(4, 3));
    }

    @Test
    void deleteBannedOptionFail() {
        Option option1 = new Option("option1", 100, 100, false, null, null,
                new HashSet<>(), new HashSet<>());
        Option option2 = new Option("option2", 100, 100, false, null, null,
                new HashSet<>(Arrays.asList(option1)), new HashSet<>());
        Option option3 = new Option("option3", 100, 100, false, null, null,
                new HashSet<>(Arrays.asList(option2)), new HashSet<>());
        Option option4 = new Option("option4", 100, 100, false, null, null,
                new HashSet<>(), new HashSet<>());
        OptionDto optionDto1 = new OptionDto("option1", 100, 100, new HashMap<>(), new HashMap<>());
        Map<Long, String> option = new HashMap<>();
        option.put(optionDto1.getId(), optionDto1.getName());
        OptionDto optionDto2 = new OptionDto("option2", 100, 100, option, new HashMap<>());
        Map<Long, String> optionMan2 = new HashMap<>();
        optionMan2.put(optionDto2.getId(), optionDto2.getName());

        Map<Long, String> optionMan = new HashMap<>();
        optionMan.put(optionDto2.getId(), optionDto2.getName());
        optionMan.put(optionDto1.getId(), optionDto1.getName());
        OptionDto optionDto = new OptionDto("option4", 100, 100, optionMan, new HashMap<>());

        Mockito.when(optionDao.getById(4)).thenReturn(option4);
        Mockito.when(optionDao.getById(3)).thenReturn(option3);

        Assert.assertFalse(optionServiceImp.deleteBannedOption(4, 3));
    }

    @Test
    void deleteBannedOptionSuccessful() {
        Option option1 = new Option("option1", 100, 100, false, null, null,
                new HashSet<>(), new HashSet<>());
        Option option2 = new Option("option2", 100, 100, false, null, null,
                new HashSet<>(Arrays.asList(option1)), new HashSet<>());
        Option option3 = new Option("option3", 100, 100, false, null, null,
                new HashSet<>(Arrays.asList(option2)), new HashSet<>());
        Option option4 = new Option("option4", 100, 100, false, null, null,
                new HashSet<>(), new HashSet<>(Arrays.asList(option3)));
        OptionDto optionDto1 = new OptionDto("option1", 100, 100, new HashMap<>(), new HashMap<>());
        Map<Long, String> option = new HashMap<>();
        option.put(optionDto1.getId(), optionDto1.getName());
        OptionDto optionDto2 = new OptionDto("option2", 100, 100, option, new HashMap<>());
        Map<Long, String> optionMan2 = new HashMap<>();
        optionMan2.put(optionDto2.getId(), optionDto2.getName());

        Mockito.when(optionDao.getById(4)).thenReturn(option4);
        Mockito.when(optionDao.getById(3)).thenReturn(option3);

        Assert.assertTrue(optionServiceImp.deleteBannedOption(4, 3));
    }

    @Test
    void splitSetMandatoryOptions() {
        Option option1 = new Option("option1", 100, 100, false, null, null,
                new HashSet<>(), new HashSet<>());
        Option option2 = new Option("option2", 100, 100, false, null, null,
                new HashSet<>(), new HashSet<>(Arrays.asList(option1)));
        Option option3 = new Option("option3", 100, 100, false, null, null,
                new HashSet<>(), new HashSet<>(Arrays.asList(option1)));

        OptionDto optionDto1 = new OptionDto("option1", 100, 100, new HashMap<>(), new HashMap<>());
        Map<Long, String> option = new HashMap<>();
        option.put(optionDto1.getId(), optionDto1.getName());
        OptionDto optionDto2 = new OptionDto("option2", 100, 100, new HashMap<>(), option);
        OptionDto optionDto3 = new OptionDto("option3", 100, 100, new HashMap<>(), option);

        Mockito.when(optionDao.getById(option3.getId())).thenReturn(option3);
        List<Option> options = new ArrayList<>(Arrays.asList(option1,option2,option3));
        Mockito.when(optionDao.getAllNotDeleted()).thenReturn(options);

        Set<OptionDto> optionDtos = new HashSet<>(Arrays.asList(optionDto1, optionDto2, optionDto3));
        Mockito.when(optionMapper.toDto(Mockito.any())).thenReturn(new OptionDto());

        Assert.assertEquals(optionServiceImp.splitSetMandatoryOptions(option3.getId()), new HashSet<>());
    }

    @Test
    void splitSetBannedOptions() {
        Option option1 = new Option("option1", 100, 100, false, null, null,
                new HashSet<>(), new HashSet<>());
        Option option2 = new Option("option2", 100, 100, false, null, null,
                new HashSet<>(Arrays.asList(option1)), new HashSet<>());
        Option option3 = new Option("option3", 100, 100, false, null, null,
                new HashSet<>(Arrays.asList(option1)), new HashSet<>());

        OptionDto optionDto1 = new OptionDto("option1", 100, 100, new HashMap<>(), new HashMap<>());
        Map<Long, String> option = new HashMap<>();
        option.put(optionDto1.getId(), optionDto1.getName());
        OptionDto optionDto2 = new OptionDto("option2", 100, 100, option, new HashMap<>());
        OptionDto optionDto3 = new OptionDto("option3", 100, 100, option, new HashMap<>());

        Mockito.when(optionDao.getById(option3.getId())).thenReturn(option3);
        List<Option> options = new ArrayList<>(Arrays.asList(option1,option2,option3));
        Mockito.when(optionDao.getAllNotDeleted()).thenReturn(options);

        Set<OptionDto> optionDtos = new HashSet<>(Arrays.asList(optionDto1, optionDto2, optionDto3));
        Mockito.when(optionMapper.toDto(Mockito.any())).thenReturn(new OptionDto());

        Assert.assertEquals(optionServiceImp.splitSetBannedOptions(option3.getId()), new HashSet<>());
    }


}