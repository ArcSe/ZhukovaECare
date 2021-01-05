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
    private String birthday;
    private int passport;
    private String email;
    private String address;
    private Set<ContractDto> contracts;
}
