package com.codegym.controller;

import com.codegym.model.EColor;
import com.codegym.model.ESize;
import com.codegym.model.Product;
import com.codegym.service.IProductService;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    @GetMapping("")
    public String list(Model model){
        List<Product> products = iProductService.findAll();
        model.addAttribute("products", products);
        return "/dashboard/products/list";
    }

    @ModelAttribute(name = "colors")
    public List<EColor> getAllColor(){
        List<EColor> colors = List.of(EColor.values());
        return colors;
    }
    @ModelAttribute(name = "sizes")
    public List<ESize> getAllSize(){
        List<ESize> sizes = List.of(ESize.values());
        return sizes;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "/dashboard/products/create";
    }

    @PostMapping("/save")
    public String save(@Validated Product product, BindingResult bindingResult) {
        FieldError fieldError = bindingResult.getFieldError("price");
        if (fieldError!=null && fieldError.contains(TypeMismatchException.class)) {
            System.out.println("Vô rooif nè");
        }
        if(bindingResult.hasErrors()){
            return "/dashboard/products/create";
        }
        product.setCreateAt(Instant.now());
        product.setDeleteAt(Instant.now());

        iProductService.save(product);
        return "redirect:/products";
    }
}
