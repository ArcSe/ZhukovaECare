package com.javaschool.dto;

import com.javaschool.model.Role;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class UserDto extends AbstractDto{
    @Size(min=8, message = "Password should have 8 characters")
    private String password;
    @Size(min=8, message = "Password should have 8 characters")
    private String passwordConfirm;
    @Email(message = "It isn't email!")
    @NotBlank(message = "The field shouldn't be empty")
    private String email;
    private boolean active;
    private Long clientId;
    private Set<Role> roles;
}
