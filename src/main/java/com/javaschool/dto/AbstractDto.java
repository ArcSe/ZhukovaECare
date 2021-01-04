package com.javaschool.dto;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
public abstract class AbstractDto {
    private long id;
}
