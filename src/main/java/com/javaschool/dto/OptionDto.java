package com.javaschool.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class OptionDto extends AbstractDto{

    @NotBlank(message = "Please fill the name")
    private String name;
    private int price;
    private int serviceCost;
    private Set<Long> mandatoryOptions;
    private Set<Long> bannedOptions;

}
