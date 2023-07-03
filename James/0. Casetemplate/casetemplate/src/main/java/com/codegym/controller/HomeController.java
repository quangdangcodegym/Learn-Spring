package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.IProductService;
import com.codegym.utils.CookieUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;

@Controller
@RequestMapping("")
public class HomeController {
    @Autowired
    private IProductService iProductService;
    @GetMapping("/")
    public String index(Model model, HttpServletResponse response, HttpServletRequest request) {
        List<Product> products = iProductService.findAll();
        model.addAttribute("products",products );





        return "frontend/index";
    }
}
