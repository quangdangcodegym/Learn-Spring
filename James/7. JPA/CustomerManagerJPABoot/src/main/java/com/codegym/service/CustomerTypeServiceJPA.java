package com.codegym.service;

import com.codegym.model.CustomerType;
import com.codegym.repository.CustomerTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CustomerTypeServiceJPA implements ICustomerTypeService{
    @Autowired
    private CustomerTypeRepository customerTypeRepository;
    @Override
    public List<CustomerType> findAll() {
        List<CustomerType> customerTypes = customerTypeRepository.findAll();
        return customerTypes;
    }

    @Override
    public CustomerType findById(Long id) {
        return customerTypeRepository.findById(id).get();
    }
}
