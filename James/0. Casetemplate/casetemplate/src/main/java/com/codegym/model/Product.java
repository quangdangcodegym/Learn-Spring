package com.codegym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;

    @Column(name = "created_at", nullable = true)
    private Instant createAt;
    @Column(name = "deleted_at", nullable = true)
    private Instant deleteAt;

    @Enumerated(EnumType.STRING)
    private EColor color;

    @Enumerated(EnumType.STRING)
    private ESize size;


}
