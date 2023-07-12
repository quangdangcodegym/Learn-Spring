package com.codegym.controller;

import com.codegym.exception.NumberInputException;
import com.codegym.exception.NumberInputExceptionWeb;
import com.codegym.model.Cart;
import com.codegym.service.ICartItemService;
import com.codegym.service.ICartService;
import com.codegym.service.IProductService;
import com.codegym.utils.AppUtils;
import com.codegym.utils.CookieUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService iCartService;
    @Autowired
    private ICartItemService iCartItemService;

    @Autowired
    private IProductService iProductService;

    @GetMapping("")
    public String index(Model model, HttpServletRequest request) {
        Cookie [] cookies = request.getCookies();
        Cart cartDB = iCartService.findByTokenIdContaining(CookieUtils.getCookieValueByName("token-id", cookies));

        if (cartDB != null) {
            model.addAttribute("cart", cartDB);
        }
        return "frontend/cart/list";
    }

    @GetMapping("/{id}/add")
    public String addCart(@PathVariable Long id, Model model,HttpServletResponse response, HttpServletRequest request) throws NumberInputException {

        /** Nếu dùng try/catch để xử lý ngoại lệ làm cho chỗ này phải xử lý 2 việc: nên dùng global exception
        try {
            cart = iCartService.addProductToCart(id,cartDB, 1, tokenId);
        } catch (NumberInputException numberInputException) {
            model.addAttribute("message", "Số lượng sản phẩm không hợp lệ");
        }
         **/
        Cart cart = iCartService.addProductToCart(id, 1, AppUtils.TYPE_WEB, request, response);
        model.addAttribute("cart", cart);
        return "frontend/cart/list";
    }


    @GetMapping("/{id}/update/{quantity}")
    public String updateCart(@PathVariable Long id,@PathVariable int quantity, Model model,
                             HttpServletResponse response, HttpServletRequest request) throws NumberInputException {

        Cart cart = iCartService.updateProductInCart(id,quantity, AppUtils.TYPE_WEB, request, response);
        model.addAttribute("cart", cart);
        return "frontend/cart/list";
    }
    @GetMapping("cart/{id}/update/{quantity}")
    public String editCart(@PathVariable Long id, @PathVariable int quantity){
        return "frontend/cart/list";
    }
}
