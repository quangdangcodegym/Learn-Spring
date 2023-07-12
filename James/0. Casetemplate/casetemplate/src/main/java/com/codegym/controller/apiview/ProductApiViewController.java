package com.codegym.controller.apiview;

import com.codegym.model.Product;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api-view/products")
public class ProductApiViewController {
    @Autowired
    private IProductService iProductService;

    @GetMapping("")
    public String list(Model model){
        List<Product> products = iProductService.findAll();
        model.addAttribute("products", products);
        return "/api-view-dashboard/products/refactor-list";
    }
}
