package com.codegym.model.dto.api;

import com.codegym.model.Product;
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
public class CartItemApiReqDTO {
    private Long id;
    private BigDecimal price;
    private Integer quantity;
    private Product product;

}
