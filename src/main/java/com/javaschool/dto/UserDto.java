package com.javaschool.dto;

import com.javaschool.model.Role;
import lombok.*;

import javax.validation.constraints.Size;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto extends AbstractDto{
    @Size(min=2, message = "Не меньше 5 знаков")
    private String password;
    @Size(min=2, message = "Не меньше 5 знаков")
    private String passwordConfirm;
    private String email;
    private boolean active;
    private Long clientId;
    private Set<Role> roles;
}
