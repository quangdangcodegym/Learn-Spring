package com.codegym.model;

import jakarta.persistence.*;
import lombok.experimental.Accessors;

import java.util.List;

@Entity
@Table(name = "customer_types")
@Accessors(chain = true)
public class CustomerType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public CustomerType(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @OneToMany(mappedBy = "customerType")
    List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public CustomerType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
