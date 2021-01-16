package com.javaschool.dto;

import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientDto extends AbstractDto {
    private String name;
    private String surname;
    private String email;
    //private String birthday;
    private int active;
    private int passport;
    private String address;
    private Set<ContractDto> contracts;
}
