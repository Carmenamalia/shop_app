package com.springapps.shop.dtos.mapper;

import com.springapps.shop.dtos.CartItemResponseDTO;
import com.springapps.shop.dtos.CartRequestDTO;
import com.springapps.shop.entities.CartItem;
import com.springapps.shop.entities.Product;
import com.springapps.shop.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CartItemMapper {

    @Mapping(target = "id",ignore = true)
    public abstract CartItem mapCartRequestDTOToCartItem(CartRequestDTO cartRequestDTO, Product product, User user);
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.price", target = "price")
    public abstract CartItemResponseDTO mapCartItemToCartItemDTO(CartItem cartItem);
}
//
