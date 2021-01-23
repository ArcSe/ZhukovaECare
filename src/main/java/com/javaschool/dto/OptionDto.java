package com.javaschool.dto;

import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class OptionDto extends AbstractDto{

    private String name;
    private int price;
    private int serviceCost;
    private Set<Long> mandatoryOptions;
    private Set<Long> bannedOptions;

}
