package com.codegym.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Cart {
    @Id
    private Long id;

    private Double total;
}
