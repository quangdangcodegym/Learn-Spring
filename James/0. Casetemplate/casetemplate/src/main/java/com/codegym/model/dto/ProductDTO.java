package com.codegym.model.dto;

import com.codegym.model.EColor;
import com.codegym.model.ESize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.Instant;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductDTO {
    private Long id;
    private EColor color;
    private Instant createdAt;
    private Instant deletedAt;
    private String description;
    private String name;
    private BigDecimal price;
    private ESize size;
    private String image;


}
