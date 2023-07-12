package com.codegym.model;

import com.codegym.model.dto.ProductDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Accessors(chain = true)
public class Product implements Serializable {
    private static final long serialVersionUID = -908L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

//    @Digits(message = "Phải là số", integer = 4, fraction = 0)
//    @DecimalMax(value = "100.00", inclusive = true, message = "xxxxxxxxxxxx")
//    @NotNull(message = "price không được null")
    private BigDecimal price;
    private String image;


    @CreationTimestamp
    @Column(name = "created_at", nullable = true)
    private Instant createAt;


    @Column(name = "deleted_at", nullable = true)
    private Instant deleteAt;

    @Enumerated(EnumType.STRING)
    private EColor color;

    @Enumerated(EnumType.STRING)
    private ESize size;

    private Integer quantity;

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
