package com.codegym.service;

import com.codegym.dto.CartDTO;
import com.codegym.model.Cart;
import com.codegym.model.CartItem;
import com.codegym.model.Product;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ICartService {
    List<Cart> findAll();

    Cart save(Cart cart);
    Cart findById(Long id);

    CartDTO findCartDTOById(Long id);

    CartDTO findCartDTOByIdUseModelMapper(Long id);

    void update(Long id, Cart cart);

    void remove(Long id);
    Cart findByTokenIdContaining(String tokenId);

    List<CartItem> getCartItems(Long cardId);


    Cart addToCart(Long idProduct, BigDecimal price, int quantity, HttpServletResponse response, HttpServletRequest request);
}
