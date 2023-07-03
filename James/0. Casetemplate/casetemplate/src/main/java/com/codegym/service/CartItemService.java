package com.codegym.service;

import com.codegym.model.Cart;
import com.codegym.model.CartItem;
import com.codegym.repository.CartItemRepository;
import com.codegym.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService implements ICartItemService{

    @Autowired
    private CartItemRepository cartItemRepository;
    @Override
    public List<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public void save(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem findById(Long id) {
        return cartItemRepository.findById(id).get();
    }

    @Override
    public void update(Long id, CartItem cartItem) {
        cartItem.setId(id);
        cartItemRepository.save(cartItem);
    }

    @Override
    public void remove(Long id) {
        CartItem cartItem = cartItemRepository.findById(id).get();
        cartItemRepository.delete(cartItem);
    }
}
