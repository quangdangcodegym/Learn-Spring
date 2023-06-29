package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.CustomerType;
import com.codegym.repository.CustomerRepository;
import com.codegym.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICustomerTypeService customerTypeService;

    // Cách sử dụng model Attributes
    @GetMapping("")
    public String index(Model model) {

        List<Customer> list = customerService.findAll();
        model.addAttribute("customers", list);
        return "views/index";
    }

    @ModelAttribute("customerTypes")
    public Iterable<CustomerType> getAllCustomerTypes() {
        System.out.println("Tạo modelAttribute");
        return customerTypeService.findAll();
    }


    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("customer", new Customer());

        return "views/create";
    }

    // Ở đây có thể sài modelAttribute
    @PostMapping("/save")
    public String save(@Validated Customer customer, BindingResult bindingResult) {
        new Customer().validate(customer, bindingResult);
        if (bindingResult.hasErrors()) {
            return "views/create";
        }

        customerService.save(customer);
        return "views/create";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "views/edit";
    }

    @PostMapping("/update")
    public String update(@Validated @ModelAttribute Customer customer,BindingResult bindingResult, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            return "views/edit";
        }
        customerService.update(customer.getId(), customer);
        redirect.addFlashAttribute("success", "Add successfully");
        return "redirect:/customers";
    }
    @GetMapping("/{id}/show")
    public String showInfo(@PathVariable Long id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/edit";
    }




}
