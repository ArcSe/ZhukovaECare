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
public class ContractDto extends AbstractDto {
    private String number;
    private boolean isLocked;
    private boolean isLockedByAdmin;
    private TariffDto tariff;
    private long clientId;
    private Set<OptionDto> options;
}
