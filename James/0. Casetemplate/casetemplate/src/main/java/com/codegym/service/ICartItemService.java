package com.codegym.service;

import com.codegym.model.dto.CartItemDTO;
import com.codegym.model.CartItem;

import java.util.List;

public interface ICartItemService {
    List<CartItem> findAll();

    void save(CartItem cart);
    CartItem findById(Long id);

    CartItemDTO findCartItemsDTOById(Long id);

    void update(Long id, CartItem cartItem);

    void remove(Long id);


}
