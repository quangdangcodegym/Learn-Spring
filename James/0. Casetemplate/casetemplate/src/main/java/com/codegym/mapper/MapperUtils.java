package com.codegym.mapper;

import com.codegym.dto.CartDTO;
import com.codegym.dto.CartItemsDTO;
import com.codegym.dto.ProductDTO;
import com.codegym.model.Cart;
import com.codegym.model.CartItem;
import com.codegym.model.Product;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.DestinationSetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapperUtils {
    private ModelMapper modelMapper;

    public ProductDTO toProductDTO(Product product) {
        modelMapper = new ModelMapper();
        // bỏ qua thuộc tính ID thì dùng propertyMapper
        TypeMap<Product, ProductDTO> propertyMapper = modelMapper.createTypeMap(Product.class, ProductDTO.class);
//        propertyMapper.addMappings(mapper -> mapper.skip(ProductDTO::setId));
        return modelMapper.map(product, ProductDTO.class);
    }
    public CartDTO toCartDTO(Cart cart) {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        /**
         // Orignal:
         TypeMap<Game, GameDTO> propertyMapper = this.mapper.createTypeMap(Game.class, GameDTO.class);
         propertyMapper.addMappings(
         mapper -> mapper.map(src -> src.getCreator().getName(), GameDTO::setCreator)
         );
         // Viết như thế này là sai
        TypeMap<Cart, CartDTO> propertyMapper = modelMapper.createTypeMap(Cart.class, CartDTO.class);
        propertyMapper.addMappings(mapper  -> {
            mapper.map(src -> {
                return src.getCartItems().stream()
                        .map(this::toCartItemDTO).collect(Collectors.toList());
            }, CartDTO::setCartItemsDTOS);
        });
         // SAi luôn:
         propertyMapper.addMappings(mapper -> mapper.map(src -> src.getTokenId().toLowerCase(), CartDTO::setTokenId));

         // SAI luoon
         TypeMap<Cart, CartDTO> propertyMapper = modelMapper.createTypeMap(Cart.class, CartDTO.class);
         Converter<List<CartItem>, List<CartItemsDTO>> listConverter = c -> c.getSource().stream().map(this::toCartItemDTO).collect(Collectors.toList());
         propertyMapper.addMappings(mapper->mapper.using(listConverter).map(Cart::getCartItems, CartDTO::setCartItemsDTOS));

         // SAI luôn - dùng skipp
         TypeMap<Cart, CartDTO> propertyMapper = modelMapper.createTypeMap(Cart.class, CartDTO.class);
         propertyMapper.addMappings(mapper -> mapper.skip(CartDTO::setCartItemsDTOS));

         CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
         List<CartItemsDTO> cartItemsDTOS = cart.getCartItems().stream().map(this::toCartItemDTO).collect(Collectors.toList());
         cartDTO.setCartItemsDTOS(cartItemsDTOS);



         // CÁCH THỦ CÔNG THÌ ĐƯỢC
         DestinationSetter c;
         TypeMap<Cart, CartDTO> propertyMapper = modelMapper.createTypeMap(Cart.class, CartDTO.class);
         propertyMapper.addMappings(mapper -> mapper.skip(CartDTO::setCartItemsDTOS));

         CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
         List<CartItemsDTO> cartItemsDTOS = cart.getCartItems().stream().map(cartItem -> {
         CartItemsDTO cartItemsDTO =  new CartItemsDTO();
         cartItemsDTO.setCartDTO(cartItem.getCart().toCartDTO());
         cartItemsDTO.setProductDTO(cartItem.getProduct().toProductDTO());
         cartItemsDTO.setPrice(cartItem.getPrice());
         cartItemsDTO.setCartId(cartItem.getCart().getId());
         cartItemsDTO.setProductId(cartItem.getProduct().getId());
         cartItemsDTO.setId(cartItem.getId());
         return cartItemsDTO;
         }).collect(Collectors.toList());

         cartDTO.setCartItemsDTOS(cartItemsDTOS);
         */
        TypeMap<Cart, CartDTO> propertyMapper = modelMapper.createTypeMap(Cart.class, CartDTO.class);
        Converter<List<CartItem>, List<CartItemsDTO>> listConverter =
                c -> c.getSource().stream().map(this::toCartItemDTO).collect(Collectors.toList());
        propertyMapper.addMappings(mapper->mapper.using(listConverter).map(Cart::getCartItems, CartDTO::setCartItemsDTOS));
        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

        return cartDTO;
    }
    public CartItemsDTO toCartItemDTO(CartItem cartItem){
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<CartItem, CartItemsDTO> propertyMapper = modelMapper.createTypeMap(CartItem.class, CartItemsDTO.class);
        propertyMapper.addMappings(
                mapper -> mapper.map(src -> this.toCartDTO(src.getCart()), CartItemsDTO::setCartDTO)
        );
        propertyMapper.addMappings(
                mapper -> mapper.map(src -> this.toProductDTO(src.getProduct()), CartItemsDTO::setProductDTO)
        );
        return modelMapper.map(cartItem, CartItemsDTO.class);
    }
}
