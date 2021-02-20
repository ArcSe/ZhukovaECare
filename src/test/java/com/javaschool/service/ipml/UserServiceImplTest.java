package com.javaschool.service.ipml;

import com.javaschool.dao.impl.UserDaoImpl;
import com.javaschool.dto.UserDto;
import com.javaschool.exception.notFound.NotDataFoundException;
import com.javaschool.mapper.UserMapper;
import com.javaschool.model.Role;
import com.javaschool.model.User;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ECareApplication.class)
//@ContextConfiguration(classes = TariffServiceConfiguration.class)
class UserServiceImplTest {

    @TestConfiguration
    static class UserServiceTestConfiguration {

        @Bean
        public UserServiceImpl userService() {
            return new UserServiceImpl();
        }
    }

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    private UserDaoImpl userDao;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private UserMapper userMapper;

    private List<User> userList;

    private List<UserDto> userDtoList;

    @BeforeEach
    void setUp() {
        //enable mocks
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAll() throws NotDataFoundException {
        userList = new ArrayList<>();
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_ADMIN);
        User user = new User("admin@admin.ru", "1", "1", true, null, roles);
        userList.add(user);

        userService.add(userMapper.toDto(user));
        Assert.assertNotNull("Contract list successfully uploaded", userService.getAll());
        //Assert.assertEquals(userList, userService.getAll());
    }

//    @Test
//    public void createUser(){
//        userList = new ArrayList<>();
//        Set<Role> roles = new HashSet<>();
//        roles.add(Role.ROLE_ADMIN);
//        User user = new User("admin@admin.ru", "1", "1", true, null, roles);
//        userList.add(user);
//
//
//        Mockito.when(patientDAO.getAll()).thenReturn(patientList);
//        Assert.assertTrue("Contract list successfully uploaded");
//    }

}
