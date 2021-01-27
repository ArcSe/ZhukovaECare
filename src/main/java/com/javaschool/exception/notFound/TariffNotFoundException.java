package com.javaschool.exception.notFound;

public class TariffNotFoundException extends Exception{

    public TariffNotFoundException(long id) {
        super(String.format("Tariff with Id %d not found", id));
    }

    public TariffNotFoundException(String name) {
        super(String.format("Tariff with name %s not found", name));
    }
}
