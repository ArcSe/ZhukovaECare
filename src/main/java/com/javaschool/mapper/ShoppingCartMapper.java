package com.javaschool.mapper;

import com.javaschool.dto.ShoppingCartDto;
import com.javaschool.model.ShoppingCart;

public class ShoppingCartMapper extends AbstractMapper<ShoppingCart, ShoppingCartDto> {

    ShoppingCartMapper(Class<ShoppingCart> entityClass, Class<ShoppingCartDto> dtoClass) {
        super(entityClass, dtoClass);
    }
}
