package com.javaschool.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ShoppingCart extends AbstractModel{
/*
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "shopingCart_option",
            joinColumns = @JoinColumn(name = "shoppingCartOption_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id"))


    private Set<Option> options;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "shoppingCart_option",
            joinColumns = @JoinColumn(name = "shoppingCart_id"),
            inverseJoinColumns = @JoinColumn(name = "tariff_id"))


    private Set<Tariff> tariffs;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id")


    private Contract contract;

    /*
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")


    private Client client;

    private String customerEmail;
    private int price;
    private int serviceCost;
    private Set<ContractDto> contracts;
    private Map<Contract, Set<OptionDto>> options;

 */
}
