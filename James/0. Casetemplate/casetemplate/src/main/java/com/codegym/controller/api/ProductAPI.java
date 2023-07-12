package com.codegym.controller.api;

import com.codegym.model.dto.ProductDTO;
import com.codegym.model.Product;
import com.codegym.service.IProductService;
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
    @GetMapping("/{id}")
    public ResponseEntity<?> findProduct(@PathVariable Long id) {
        ProductDTO productDTO = iProductService.findProductDTOByID(id);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> saveProduct( @RequestBody Product product){
        iProductService.save(product);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("")
    public ResponseEntity<?> updateProduct( @RequestBody Product product){
        iProductService.update(product.getId(),product);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@Validated @RequestBody Product product){
        iProductService.remove(product.getId());
        return ResponseEntity.noContent().build();
    }

}
