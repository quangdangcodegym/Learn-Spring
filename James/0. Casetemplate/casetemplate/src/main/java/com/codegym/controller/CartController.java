package com.codegym.controller;

import com.codegym.config.Config;
import com.codegym.model.Cart;
import com.codegym.model.Product;
import com.codegym.service.ICartItemService;
import com.codegym.service.ICartService;
import com.codegym.service.IProductService;
import com.codegym.utils.CookieUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.RequestContextHolder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Controller
public class CartController {
    @Autowired
    private ICartService iCartService;
    @Autowired
    private ICartItemService iCartItemService;

    @Autowired
    private IProductService iProductService;

    @GetMapping("/cart")
    public String index(Model model, HttpServletRequest request) {
        Cookie [] cookies = request.getCookies();
        Cart cartDB = iCartService.findByTokenIdContaining(CookieUtils.getCookieValueByName("token-id", cookies));

        if (cartDB != null) {
            model.addAttribute("cart", cartDB);
        }
        return "frontend/cart/list";
    }




    @GetMapping("cart/{id}/add")
    public String addCart(@PathVariable Long id, Model model,HttpServletResponse response, HttpServletRequest request) {
//        CookieUtils.readCookies(request.getCookies());
        Product product = iProductService.findById(id);
        Cart cart = iCartService.addToCart(id, product.getPrice(), 1, response, request);


        model.addAttribute("cart", cart);
        return "frontend/cart/list";
    }
    @GetMapping("cart/{id}/update/{quantity}")
    public String editCart(@PathVariable Long id, @PathVariable int quantity){
        return "frontend/cart/list";
    }
}
