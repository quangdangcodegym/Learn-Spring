package com.codegym.controller.api;

import com.codegym.model.dto.CartItemDTO;
import com.codegym.service.ICartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cartitems")
public class CartItemAPI {
    @Autowired
    private ICartItemService iCartItemService ;

    @GetMapping("/{id}")
    public ResponseEntity<?> listCartItemDTOs(@PathVariable Long id){
        CartItemDTO cartDTO = iCartItemService.findCartItemsDTOById(id);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }
}
