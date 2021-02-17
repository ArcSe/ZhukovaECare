package com.javaschool.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
//@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = TariffDto.class)
public class TariffDto extends AbstractDto implements Serializable {

    @Pattern(message = "Bad formed name, should be only words and numbers",
            regexp = "[0-9a-zA-Z]*")
    @NotBlank(message = "Name shouldn't be empty")
    private String name;
    private int price = 0;
    private int serviceCost = 0;
    private Set<OptionDto> options;
}
