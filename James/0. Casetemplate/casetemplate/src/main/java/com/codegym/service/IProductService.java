package com.codegym.service;


import com.codegym.dto.ProductDTO;
import com.codegym.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    List<ProductDTO> findAllProductDTOs();

    void save(Product customer);
    Product findById(Long id);

    void update(Long id, Product customer);

    void remove(Long id);
}
