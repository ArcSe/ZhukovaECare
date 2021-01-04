package com.javaschool.mapper;


import com.javaschool.dto.AbstractDto;
import com.javaschool.model.AbstractModel;

public interface Mapper<E extends AbstractModel, D extends AbstractDto> {

    E toEntity(D dto);

    D toDto(E entity);
}
