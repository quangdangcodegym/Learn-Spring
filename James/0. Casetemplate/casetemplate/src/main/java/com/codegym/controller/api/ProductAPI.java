package com.codegym.api;

import com.codegym.dto.CartDTO;
import com.codegym.dto.ProductDTO;
import com.codegym.model.Product;
import com.codegym.service.ICartService;
import com.codegym.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create/{id}")
    public ResponseEntity<?> saveProduct(@Validated @RequestBody Product product, @PathVariable Long id){
        iProductService.save(product);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }


}
