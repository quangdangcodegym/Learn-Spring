package com.codegym.service;

import com.codegym.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ICustomerService {
    List<Customer> findAll();

    void save(Customer customer);

    Customer findById(Long id);

    void update(Long id, Customer customer);

    void remove(Long id);
}
