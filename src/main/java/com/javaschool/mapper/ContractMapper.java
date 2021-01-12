package com.javaschool.mapper;

import com.javaschool.dao.ClientDao;
import com.javaschool.dao.TariffDao;
import com.javaschool.dto.ContractDto;
import com.javaschool.model.Client;
import com.javaschool.model.Contract;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class ContractMapper extends AbstractMapper<Contract, ContractDto> {

    private final ModelMapper mapper;
    private final ClientDao clientDao;
    private final TariffDao tariffDao;

    @Autowired
    ContractMapper(ModelMapper mapper, TariffDao tariffDao, ClientDao clientDao) {
        super(Contract.class, ContractDto.class);
        this.mapper = mapper;
        this.clientDao = clientDao;
        this.tariffDao = tariffDao;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Contract.class, ContractDto.class)
                .addMappings(m -> m.skip(ContractDto::setClientId))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(ContractDto.class, Contract.class)
                .addMappings(m -> m.skip(Contract::setClient)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(Contract source, ContractDto destination) {
        if(!Objects.isNull(getId(source))){
            destination.setClientId(getId(source));
        }

    }

    private Long getId(Contract source) {
        return Objects.isNull(source) || Objects.isNull(source.getClient()) ? null : source.getClient().getId();
    }

    @Override
    void mapSpecificFields(ContractDto source, Contract destination) {
        destination.setClient(clientDao.getById(source.getClientId()));
    }
}
