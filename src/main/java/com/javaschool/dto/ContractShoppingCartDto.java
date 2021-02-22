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
public class ContractShoppingCartDto extends AbstractDto {
    private int price;
    private int serviceCost;
    private Set<OptionDto> optionsShoppingCart;
    private ContractDto contract;
}
