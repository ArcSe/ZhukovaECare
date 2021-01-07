package com.javaschool.dto;

import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class TariffDto extends AbstractDto{

    private String name;
    private Set<OptionDto> options;
}
