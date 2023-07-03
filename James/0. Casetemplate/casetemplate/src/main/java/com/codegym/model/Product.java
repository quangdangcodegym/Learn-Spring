package com.codegym.model;

import com.codegym.dto.ProductDTO;
import jakarta.persistence.*;
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
@Entity
@Table
@Accessors(chain = true)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String image;


    @Column(name = "created_at", nullable = true)
    private Instant createAt;
    @Column(name = "deleted_at", nullable = true)
    private Instant deleteAt;

    @Enumerated(EnumType.STRING)
    private EColor color;

    @Enumerated(EnumType.STRING)
    private ESize size;

    public ProductDTO toProductDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(this.getName())
                .setId(this.getId())
                .setPrice(this.getPrice())
                .setDescription(this.getDescription())
                .setImage(this.getImage())
                .setColor(this.getColor())
                .setDeletedAt(this.getDeleteAt())
                .setCreatedAt(this.getCreateAt())
                .setSize(this.getSize());

        return productDTO;
    }


}
