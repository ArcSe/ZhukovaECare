package com.javaschool.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientDto extends AbstractDto {
    @Pattern(message = "Bad formed name, should be only words",
            regexp = "^[A-Z][a-z]*")
    @NotNull(message = "Name shouldn't be empty")
    private String name;

    @Pattern(message = "Bad formed surname, should be only words",
            regexp = "^[A-Z][a-z]*")
    @NotNull(message = "Surname shouldn't be empty")
    private String surname;

    private String email;

    private String birthday;

    private int active;

    @Pattern(message = "Bad formed passport",
            regexp = "\\d{4}\\s\\d{6}")
    private String passport;

    @NotBlank(message = "Address shouldn't be empty")
    private String address;

    private Set<ContractDto> contracts;
}
