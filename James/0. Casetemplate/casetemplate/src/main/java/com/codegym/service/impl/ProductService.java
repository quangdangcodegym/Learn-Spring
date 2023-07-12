package com.codegym.service;

import com.codegym.dto.ProductDTO;
import com.codegym.mapper.MapperUtils;
import com.codegym.model.Product;
import com.codegym.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MapperUtils mapperUtils;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductDTO> findAllProductDTOs() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = products.stream().map(mapperUtils::toProductDTO).collect(Collectors.toList());
        return productDTOS;
    }
    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public ProductDTO findProductDTOByID(Long id) {
        Product product = productRepository.findById(id).get();
        return mapperUtils.toProductDTO(product);
    }

    @Override
    public void update(Long id, Product product) {
        product.setId(id);
        productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        Product product = productRepository.findById(id).get();
        productRepository.save(product);
    }
}
