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
    private BCryptPasswordEncoder bCryptPasswordEncoder ;

    @Mock
    private UserMapper userMapper;

    private List<UserDto> userList;

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
        //userList.add(user);

        userService.add(userMapper.toDto(user));
        Assert.assertNotNull("Contract list successfully uploaded", userService.getAll());
        //Assert.assertEquals(userList, userService.getAll());
    }

    @Test
    public void createUserSuccessful(){
        userList = new ArrayList<>();
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_ADMIN);
        UserDto user = new UserDto("password", "password", "admin@admin.ru", true, null, roles);
        userList.add(user);

        Mockito.when(bCryptPasswordEncoder.encode(Mockito.any())).thenReturn("password");
        Assert.assertEquals("password", user.getPassword());

        Assert.assertTrue(userService.save(user));
    }

    @Test
    public void createUserFail(){
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_ADMIN);
        String email = "admin@admin.ru";
        String password = "password";
        User user = new User(password, password, email, true, null, roles);
        UserDto userDto = new UserDto(password, password, email, true, null, roles);

        Mockito.when(bCryptPasswordEncoder.encode(Mockito.any())).thenReturn("password");
        Assert.assertEquals("password", user.getPassword());

        Mockito.when(userService.getByUserEmail(email)).thenReturn(user);

        Assert.assertFalse(userService.save(userDto));
    }


    @Test
    void getByUserEmail() {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_ADMIN);
        String email = "admin@admin.ru";
        String password = "password";
        User user = new User(password, password, email, true, null, roles);

        Mockito.when(userDao.getByEmail(email)).thenReturn(user);
        Assert.assertEquals(user, userService.getByUserEmail(email));
    }

}
