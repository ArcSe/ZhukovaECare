package com.javaschool.mapper;

import com.javaschool.dao.ClientDao;
import com.javaschool.dto.ContractDto;
import com.javaschool.dto.OptionDto;
import com.javaschool.model.Contract;
import com.javaschool.model.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ContractMapper /*extends AbstractMapper<Contract, ContractDto>*/ {

    private OptionMapper optionMapper;
    private TariffMapper tariffMapper;
    private ClientDao clientDao;

    @Autowired
    public ContractMapper(OptionMapper optionMapper, TariffMapper tariffMapper, ClientDao clientDao) {
        this.optionMapper = optionMapper;
        this.tariffMapper = tariffMapper;
        this.clientDao = clientDao;
    }

    public Contract toEntity(ContractDto dto){
        Contract contract = new Contract();
        contract.setId(dto.getId());
        contract.setNumber(dto.getNumber());
        contract.setLocked(dto.isLocked());
        contract.setLockedByAdmin(dto.isLockedByAdmin());
        Set<Option> optionSet = dto.getOptions().stream().map(optionDto -> optionMapper.toEntity(optionDto)).collect(Collectors.toSet());
        contract.setOptions(optionSet);
        contract.setTariff(tariffMapper.toEntity(dto.getTariff()));
        contract.setClient(clientDao.getById(dto.getClientId() ));
        return contract;
    }

    public ContractDto toDto(Contract dto){
        ContractDto contract = new ContractDto();
        contract.setId(dto.getId());
        contract.setNumber(dto.getNumber());
        contract.setLocked(dto.isLocked());
        contract.setLockedByAdmin(dto.isLockedByAdmin());
        Set<OptionDto> optionSet = dto.getOptions().stream().map(option -> optionMapper.toDto(option)).collect(Collectors.toSet());
        contract.setOptions(optionSet);
        contract.setTariff(tariffMapper.toDto(dto.getTariff()));
        if(dto.getClient() != null) {
            contract.setClientId(dto.getClient().getId());
        }
        return contract;
    }

//    private final ModelMapper mapper;
//    private final ClientDao clientDao;
//
//    @Autowired
//    ContractMapper(ModelMapper mapper, ClientDao clientDao) {
//        super(Contract.class, ContractDto.class);
//        this.mapper = mapper;
//        this.clientDao = clientDao;
//    }
//
//    @PostConstruct
//    public void setupMapper() {
//        mapper.createTypeMap(Contract.class, ContractDto.class)
//                .addMappings(m -> m.skip(ContractDto::setClientId))
//                .setPostConverter(toDtoConverter());
//        mapper.createTypeMap(ContractDto.class, Contract.class)
//                .addMappings(m -> m.skip(Contract::setClient)).setPostConverter(toEntityConverter());
//    }
//
//    @Override
//    public void mapSpecificFields(Contract source, ContractDto destination) {
//        if(!Objects.isNull(getId(source))){
//            destination.setClientId(getId(source));
//        }
//
//    }
//
//    private Long getId(Contract source) {
//        return Objects.isNull(source) || Objects.isNull(source.getClient()) ? null : source.getClient().getId();
//    }
//
//    @Override
//    void mapSpecificFields(ContractDto source, Contract destination) {
//        destination.setClient(clientDao.getById(source.getClientId()));
//    }
}
