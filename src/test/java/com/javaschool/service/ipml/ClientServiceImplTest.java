package com.javaschool.service.ipml;

import com.javaschool.dao.impl.ClientDaoImpl;
import com.javaschool.dto.ClientDto;
import com.javaschool.exception.notFound.ExamplesNotFoundException;
import com.javaschool.mapper.ClientMapper;
import com.javaschool.model.Client;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.*;

@RunWith(SpringRunner.class)
class ClientServiceImplTest {

    @TestConfiguration
    static class ClientServiceTestConfiguration {
        @Bean
        public ClientServiceImpl userService() {
            return new ClientServiceImpl();
        }
    }

    @InjectMocks
    ClientServiceImpl clientService;

    @Mock
    private ClientDaoImpl clientDao;

    @Mock
    private ClientMapper clientMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAll() {
        ClientDto clientDto = new ClientDto("Ivan", "Ivanov", "a@a.ru", "2003-01-31", 1, "1234 123456", "address", null);
        ClientDto clientDto1 = new ClientDto("Iva", "Ivanov", "i@i.ru", "2003-01-31", 1, "1233 123456", "address", null);
        Client client = new Client("Ivan", "Ivanov", LocalDate.of(2003,01,31),  "1234 123456", "address", null, null);
        Client client1 = new Client("Iva", "Ivanov", LocalDate.of(2003,01,31),  "1233 123456", "address", null, null);

        List<Client> clients = new ArrayList<>(Arrays.asList(client, client1));
        Mockito.when(clientDao.getAll()).thenReturn(clients);
        Assert.assertEquals(clients.size(), clientService.getAll().size());
    }

    @Test
    void addSuccessful() {
        ClientDto clientDto = new ClientDto("Ivan", "Ivanov", "a@a.ru", "2003-01-31", 1, "1234 123456", "address", null);
        ClientDto clientDto1 = new ClientDto("Iva", "Ivanov", "i@i.ru", "2003-01-31", 1, "1233 123456", "address", null);
        Client client = new Client("Ivan", "Ivanov", LocalDate.of(2003,01,31),  "1234 123456", "address", null, null);
        Client client1 = new Client("Iva", "Ivanov", LocalDate.of(2003,01,31),  "1233 123456", "address", null, null);

        Mockito.when(clientDao.getByPassport("1234 123456")).thenReturn(null);
        Mockito.when(clientMapper.toEntity(clientDto)).thenReturn(client);
        Assert.assertTrue(clientService.add(clientDto));

    }

    @Test
    void addFail() {
        ClientDto clientDto = new ClientDto("Ivan", "Ivanov", "a@a.ru", "2003-01-31", 1, "1234 123456", "address", null);
        ClientDto clientDto1 = new ClientDto("Iva", "Ivanov", "i@i.ru", "2003-01-31", 1, "1233 123456", "address", null);
        Client client = new Client("Ivan", "Ivanov", LocalDate.of(2003,01,31),  "1234 123456", "address", null, null);
        Client client1 = new Client("Iva", "Ivanov", LocalDate.of(2003,01,31),  "1233 123456", "address", null, null);

        Mockito.when(clientDao.getByPassport("1234 123456")).thenReturn(client);
        Mockito.when(clientMapper.toEntity(clientDto)).thenReturn(client);
        Assert.assertFalse(clientService.add(clientDto));
    }

    @Test
    void getByIdSuccessful() {
        ClientDto clientDto = new ClientDto("Ivan", "Ivanov", "a@a.ru", "2003-01-31", 1, "1234 123456", "address", null);
        ClientDto clientDto1 = new ClientDto("Iva", "Ivanov", "i@i.ru", "2003-01-31", 1, "1233 123456", "address", null);
        Client client = new Client("Ivan", "Ivanov", LocalDate.of(2003,01,31),  "1234 123456", "address", null, null);
        Client client1 = new Client("Iva", "Ivanov", LocalDate.of(2003,01,31),  "1233 123456", "address", null, null);

        List<Client> clients = new ArrayList<>(Arrays.asList(client, client1));

        Mockito.when(clientDao.getById(1)).thenReturn(client);
        Mockito.when(clientMapper.toDto(client) ).thenReturn(clientDto);
        try {
            Assert.assertEquals(clientDto, clientService.getById(1));
        } catch (ExamplesNotFoundException e) {
            Assert.fail();
        }
    }

    @Test
    void getByIdFail() {
        ClientDto clientDto = new ClientDto("Ivan", "Ivanov", "a@a.ru", "2003-01-31", 1, "1234 123456", "address", null);
        ClientDto clientDto1 = new ClientDto("Iva", "Ivanov", "i@i.ru", "2003-01-31", 1, "1233 123456", "address", null);
        Client client = new Client("Ivan", "Ivanov", LocalDate.of(2003,01,31),  "1234 123456", "address", null, null);
        Client client1 = new Client("Iva", "Ivanov", LocalDate.of(2003,01,31),  "1233 123456", "address", null, null);

        List<Client> clients = new ArrayList<>(Arrays.asList(client, client1));

        Mockito.when(clientDao.getById(1)).thenReturn(null);
        Mockito.when(clientMapper.toDto(client) ).thenReturn(clientDto);
        String message = null;
        try {
             clientService.getById(1);
        } catch (ExamplesNotFoundException e) {
            message = "ExamplesNotFoundException";
        }
        Assert.assertEquals("ExamplesNotFoundException", message);
    }

    @Test
    void getClientDtoForUserProfile() {
        ClientDto clientDto = new ClientDto("Ivan", "Ivanov", "a@a.ru", "2003-01-31", 1, "1234 123456", "address", null);
        Client client = new Client("Ivan", "Ivanov", LocalDate.of(2003,01,31),  "1234 123456", "address", null, null);
        Client client1 = new Client("Iva", "Ivanov", LocalDate.of(2003,01,31),  "1233 123456", "address", null, null);
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_ADMIN);
        String email = "a@a.ru";
        String password = "password";
        client.setId(1);
        User user = new User(password, password, email, true, client, roles);
        List<Client> clients = new ArrayList<>(Arrays.asList(client, client1));

        Mockito.when(clientDao.getById(1)).thenReturn(client);
        Mockito.when(clientDao.getById(1)).thenReturn(client);
        Mockito.when(clientMapper.toDto(client)).thenReturn(clientDto);
        Assert.assertEquals(clientDto, clientService.getClientDtoForUserProfile(user));
    }
}