package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceJPA implements ICustomerService{

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<Customer> findAll() {
        List<Customer> customers = customerRepository.findAll();
//        customers.stream().forEach(customer -> System.out.println(customer.getCustomerType().getName()));
        return customers;
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void update(Long id, Customer customer) {
        customer.setId(id);
        customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {

    }
}
