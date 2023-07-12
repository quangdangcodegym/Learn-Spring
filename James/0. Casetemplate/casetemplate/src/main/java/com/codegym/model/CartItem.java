package com.codegym.model;

import com.codegym.model.dto.CartItemDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

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

    @Column(name = "product_id", insertable = false, updatable = false)
    private Long pId;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id", nullable = false)
    private Cart cart;


    private Integer quantity;
    private BigDecimal price;

    //CartItem(null, product, cart, quantity, price)
    public CartItem(Long id, Product product, Cart cart, int quantity, BigDecimal price) {
        this.id = id;
        this.product = product;
        this.cart = cart;
        this.quantity = quantity;
        this.price = price;
    }


    public CartItemDTO toCartItemsDTO() {
        CartItemDTO cartItemsDTO = new CartItemDTO();

        cartItemsDTO.setId(this.getId())
                .setPrice(this.getPrice())
                .setQuantity(this.getQuantity());
        return cartItemsDTO;
    }
    public CartItemDTO toCartItemsDTO(CartItem cartItem) {
        CartItemDTO cartItemsDTO = new CartItemDTO();

        cartItemsDTO.setId(cartItem.getId())
                .setPrice(cartItem.getPrice())
                .setQuantity(cartItem.getQuantity());
        return cartItemsDTO;
    }

}
