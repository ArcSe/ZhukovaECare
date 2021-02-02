package com.javaschool.dto;

import lombok.*;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class TariffDto extends AbstractDto{

    @Pattern(message = "Bad formed name, should be only words and numbers",
            regexp = "[0-9a-zA-Z]*")
    @NotBlank(message = "Name shouldn't be empty")
    private String name;
    private int price=0;
    private Set<OptionDto> options;
}
