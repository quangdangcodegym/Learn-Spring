package com.codegym.controller.api;

import com.codegym.model.dto.api.CartApiResDTO;
import com.codegym.model.dto.api.CartItemApiReqDTO;
import com.codegym.exception.NumberInputException;
import com.codegym.model.Cart;
import com.codegym.service.ICartService;
import com.codegym.utils.AppUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartAPI {
    @Autowired
    private ICartService iCartService ;

    public CartAPI() {
        System.out.println("Chay vao init");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listCartDTOs(@PathVariable Long id){
        CartApiResDTO cartApiResDTO = iCartService.findCartApiResDTO(id);
        return new ResponseEntity<>(cartApiResDTO, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addProductToCart(@RequestBody CartItemApiReqDTO cartItemApiReqDTO, HttpServletRequest request, HttpServletResponse response) throws NumberInputException {
        Cart cart = iCartService.addProductToCart(cartItemApiReqDTO.getProduct().getId(), 1, AppUtils.TYPE_API, request, response);
        CartApiResDTO cartApiResDTO = iCartService.findCartApiResDTO(cart.getId());
        return new ResponseEntity<>(cartApiResDTO, HttpStatus.OK);
    }
    @PutMapping("")
    public ResponseEntity<?> updateProductInCart(@RequestBody CartItemApiReqDTO cartItemApiReqDTO, BindingResult bindingResult,
                                                 HttpServletRequest request, HttpServletResponse response) throws NumberInputException{

        Cart cart = iCartService.updateProductInCart(cartItemApiReqDTO.getProduct().getId(), cartItemApiReqDTO.getQuantity(), AppUtils.TYPE_API, request, response);
        CartApiResDTO cartApiResDTO = iCartService.findCartApiResDTO(cart.getId());
        return new ResponseEntity<>(cartApiResDTO, HttpStatus.OK);
    }
}
