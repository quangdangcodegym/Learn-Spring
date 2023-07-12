package com.codegym.model.dto.api;

import com.codegym.model.dto.CartItemDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CartApiResDTO {
    private Long id;
    private Instant createAt;
    private String tokenId;
    private Double total;
    private Long userId;
    private LocalDateTime expiratedDate;

    List<CartItemDTO> cartItems;

}
