package com.codegym.service;

import com.codegym.model.CustomerType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerTypeService implements ICustomerTypeService{
    private static final Map<Integer, CustomerType> customerTypes;

    static {

        customerTypes = new HashMap<>();
        customerTypes.put(1, new CustomerType(1, "VIP"));
        customerTypes.put(2, new CustomerType(2, "SUPER VIP"));
    }
    @Override
    public List<CustomerType> findAll() {
        return new ArrayList<>(customerTypes.values());
    }

    @Override
    public CustomerType findById(int id) {
        return customerTypes.get(id);
    }
}
