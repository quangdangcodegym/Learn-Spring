package com.codegym.service;

import com.codegym.model.dto.CartDTO;
import com.codegym.model.dto.api.CartApiResDTO;
import com.codegym.exception.NumberInputException;
import com.codegym.model.Cart;
import com.codegym.model.CartItem;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface ICartService {
    List<Cart> findAll();

    Cart save(Cart cart);
    Cart findById(Long id);

    CartDTO findCartDTOById(Long id);

    void update(Long id, Cart cart);

    void remove(Long id);
    Cart findByTokenIdContaining(String tokenId);

    List<CartItem> getCartItems(Long cardId);



    Cart addProductToCart(Long idProduct, int quantity, String type, HttpServletRequest request, HttpServletResponse response) throws NumberInputException;

    Cart updateProductInCart(Long id, int quantity, String type, HttpServletRequest request, HttpServletResponse response) throws NumberInputException;

    CartDTO findCartDTOByIdUseModelMapper(Long id);

    CartApiResDTO findCartApiResDTO(Long id);
}
