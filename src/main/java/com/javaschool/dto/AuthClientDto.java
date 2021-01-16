package com.javaschool.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthClientDto extends AbstractDto{
    private String password;
    private String email;
}
