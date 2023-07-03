package com.codegym.model;

import com.codegym.dto.CartDTO;
import com.codegym.dto.CartItemsDTO;
import com.codegym.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id", nullable = false)
    private Cart cart;


    private Integer quantity;
    private BigDecimal price;


    public CartItemsDTO toCartItemsDTO() {
        CartItemsDTO cartItemsDTO = new CartItemsDTO();

        cartItemsDTO.setId(this.getId())
                .setPrice(this.getPrice())
                .setQuantity(this.getQuantity())
                .setCartId(this.getCart().getId())
                .setProductId(this.getProduct().getId());
        return cartItemsDTO;
    }
    public CartItemsDTO toCartItemsDTO(CartItem cartItem) {
        CartItemsDTO cartItemsDTO = new CartItemsDTO();

        cartItemsDTO.setId(cartItem.getId())
                .setPrice(cartItem.getPrice())
                .setQuantity(cartItem.getQuantity())
                .setCartId(cartItem.getCart().getId())
                .setProductId(cartItem.getProduct().getId());
        return cartItemsDTO;
    }

}
