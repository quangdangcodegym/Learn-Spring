package com.codegym.mapper;

import com.codegym.model.User;
import com.codegym.model.dto.CartDTO;
import com.codegym.model.dto.CartItemDTO;
import com.codegym.model.dto.ProductDTO;
import com.codegym.model.dto.api.CartApiResDTO;
import com.codegym.model.Cart;
import com.codegym.model.CartItem;
import com.codegym.model.Product;
import com.codegym.service.auth.request.RegisterRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapperUtils {
    @Autowired
    private ModelMapper modelMapper;

    public ProductDTO toProductDTO(Product product) {
        /**
        TypeMap<Product, ProductDTO> typeMap = modelMapper.getTypeMap(Product.class, ProductDTO.class);
        if(typeMap !=null)
            typeMap.addMappings(mapper -> mapper.skip(ProductDTO::setId));
         **/
        return modelMapper.map(product, ProductDTO.class);
    }
    public CartDTO toCartDTO(Cart cart) {
        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
        return cartDTO;
    }
    public CartApiResDTO toCartApiResDTO(Cart cart) {
        CartApiResDTO cartApiResDTO = modelMapper.map(cart, CartApiResDTO.class);
        return cartApiResDTO;
    }
    public CartItemDTO toCartItemDTO(CartItem cartItem){
        return modelMapper.map(cartItem, CartItemDTO.class);
    }

    public User toUser(RegisterRequest request) {
        User user = modelMapper.map(request, User.class);
        return user;
    }

}
