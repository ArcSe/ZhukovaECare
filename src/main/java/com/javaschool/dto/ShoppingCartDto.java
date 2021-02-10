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
public class ShoppingCartDto extends AbstractDto {
    private Set<ContractShoppingCartDto> contracts;
    private String customerEmail;
    private int price;
    private int serviceCost;
}
