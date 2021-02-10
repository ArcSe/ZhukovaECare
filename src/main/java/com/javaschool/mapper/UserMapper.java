package com.javaschool.mapper;

import com.javaschool.dao.ClientDao;
import com.javaschool.dao.TariffDao;
import com.javaschool.dto.UserDto;
import com.javaschool.dto.UserDto;
import com.javaschool.model.User;
import com.javaschool.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class UserMapper extends AbstractMapper<User, UserDto> {
    private final ModelMapper mapper;
    private final ClientDao clientDao;
        
    @Autowired
    UserMapper(ModelMapper mapper, ClientDao clientDao) {
        super(User.class, UserDto.class);
        this.mapper = mapper;
        this.clientDao = clientDao;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(User.class, UserDto.class)
                .addMappings(m -> m.skip(UserDto::setClientId))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(UserDto.class, User.class)
                .addMappings(m -> m.skip(User::setClient)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(User source, UserDto destination) {
        if(!Objects.isNull(getId(source))){
            destination.setClientId(getId(source));
        }

    }

    private Long getId(User source) {
        return Objects.isNull(source) || Objects.isNull(source.getClient()) ? null : source.getClient().getId();
    }

    @Override
    void mapSpecificFields(UserDto source, User destination) {
        if(!Objects.isNull(source.getClientId())){
            destination.setClient(clientDao.getById(source.getClientId()));
        }
    }
}
