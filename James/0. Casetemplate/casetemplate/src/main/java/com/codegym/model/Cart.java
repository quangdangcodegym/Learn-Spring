package com.codegym.model;

import com.codegym.model.dto.CartDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@Table
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double total;

    @Column(name = "create_at")
    @CreationTimestamp
    private Instant createAt;
    @Column(name = "user_id")
    private Long userID;

    @Column(name = "token_id")
    private String tokenId;

    @Column(name = "expirated_date", nullable = false)
    private LocalDateTime expiratedDate;


    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    List<CartItem> cartItems;


    public CartDTO toCartDTO(){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(id)
                .setCreateAt(this.createAt)
                .setTokenId(this.tokenId)
                .setTotal(this.total)
                .setUserId(this.userID)
                .setExpiratedDate(this.expiratedDate);

        System.out.println();
        return cartDTO;
    }

}
