package com.javaschool.mapper;

import com.javaschool.dao.UserDao;
import com.javaschool.dto.ClientDto;
import com.javaschool.dto.ContractDto;
import com.javaschool.model.Client;
import com.javaschool.model.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Component
public class ClientMapper extends AbstractMapper<Client, ClientDto> {

    final
    UserMapper userMapper;
    final UserDao userDao;

    @Autowired
    ClientMapper(UserMapper userMapper, UserDao userDao) {
        super(Client.class, ClientDto.class);
        this.userMapper = userMapper;
        this.userDao = userDao;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Client.class, ClientDto.class)
                .addMappings(m -> {m.skip(ClientDto::setEmail); m.skip(ClientDto::setBirthday);} )
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(ClientDto.class, Client.class)
                .addMappings(m -> {m.skip(Client::setUser); m.skip(Client::setBirthday);})
                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(Client source, ClientDto destination) {
        if(!Objects.isNull(getEmail(source))){
            destination.setEmail(getEmail(source));
        }
        if(!Objects.isNull(source.getBirthday())){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateTime = source.getBirthday();
            destination.setBirthday(dateTime.format(formatter));
        }

    }

    private String getEmail(Client source) {
        return Objects.isNull(source) || Objects.isNull(source.getUser()) ? null : source.getUser().getEmail();
    }

    @Override
    void mapSpecificFields(ClientDto source, Client destination) {
        if(!Objects.isNull(source.getEmail())) {
            destination.setUser(userDao.getByEmail(source.getEmail()));
        }
        if(!Objects.isNull(source.getBirthday())){
            String str = source.getBirthday();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateTime = LocalDate.parse(str, formatter);
            destination.setBirthday(dateTime);
        }
    }
}
