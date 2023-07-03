package com.codegym.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CartItemsDTO {
    private Long id;
    private BigDecimal price;
    private Integer quantity;
    private Long cartId;
    private CartDTO cartDTO;
    private Long productId;
    private ProductDTO productDTO;

}
