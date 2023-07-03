package com.codegym.api;

import com.codegym.dto.CartDTO;
import com.codegym.dto.ProductDTO;
import com.codegym.service.ICartService;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductAPI {
    @Autowired
    private IProductService iProductService ;



    @GetMapping()
    public ResponseEntity<?> listProducts() {
        List<ProductDTO> productDTOs = iProductService.findAllProductDTOs();

        return new ResponseEntity<>(productDTOs, HttpStatus.OK);
    }


}
