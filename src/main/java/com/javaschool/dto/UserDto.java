package com.javaschool.dto;

import com.javaschool.model.Role;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto extends AbstractDto{
    private String password;
    private String email;
    private boolean active;
    private Set<Role> roles;
}
