package com.javaschool.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class OptionDto extends AbstractDto implements Serializable {

    @Pattern(message = "Bad formed name, should be only words and numbers",
            regexp = "[0-9a-zA-Z-\\s]*")
    @NotBlank(message = "Name shouldn't be empty")
    private String name;
    @DecimalMax(message = "Option price shouldn't be more than 5000",
            value = "5000")
    @DecimalMin(message = "Option price should be negative",
            value = "-1", inclusive = false)
    private int price;
    @DecimalMax(message = "Option service cost shouldn't be more than 5000",
            value = "5000")
    @DecimalMin(message = "Option service cost should be negative",
            value = "-1", inclusive = false)
    private int serviceCost;
    private Map<Long, String> mandatoryOptions;
    private Map<Long, String> bannedOptions;


}
