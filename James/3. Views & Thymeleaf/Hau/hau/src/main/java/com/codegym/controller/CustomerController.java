package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import com.codegym.service.ICustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    public CustomerController() {
        System.out.println("Khởi taạo controller");
    }
    private final ICustomerService customerService = new CustomerService();

//    @GetMapping("")
//    public String index(Model model) {
//
//        List<Customer> customerList = customerService.findAll();
//        model.addAttribute("customers", customerList);
//        return "/index";
//    }

    // Cách sử dụng model Attributes
//    @GetMapping("")
//    public String index(Model model) {
//
////        List<Customer> customerList = customerService.findAll();
////        model.addAttribute("customers", customerList);
//        return "/index";
//    }

    @GetMapping("")
    public ModelAndView index1(){
        ModelAndView modelAndView = new ModelAndView("/index");
//        List<Customer> customerList = customerService.findAll();
//        modelAndView.addObject("customers", customerList);
        return modelAndView;
    }

    // Định nghĩa 1 model @ModelAttribute("customers")
    @ModelAttribute("customers")
    public Iterable<Customer> getAllCustomers() {
        System.out.println("Chay vao ModelAttribute");
        return customerService.findAll();
    }



    @GetMapping("/create")
    public String create(Model model) {
        Customer c = new Customer();
        System.out.println("Create: " + c);
        model.addAttribute("customer", new Customer());

        return "/create";
    }
//    @PostMapping("/save")
//    public String save(Customer customer, Model model) {
//        customer.setId((int) (Math.random() * 10000));
//        customerService.save(customer);
//
//        model.addAttribute("customer", new Customer());
//        return "/create";
//    }

    // Ở đây có thể sài modelAttribute
    @PostMapping("/save")
    public String save(Customer customer) {
        System.out.println("Save customer :" + customer);
//        System.out.println("Save model :" + model.getAttribute("customer"));
        customer.setId((int) (Math.random() * 10000));
        customerService.save(customer);

//        model.addAttribute("customer", new Customer());
        return "/create";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Customer customer, RedirectAttributes redirect) {
        customerService.update(customer.getId(), customer);

        redirect.addFlashAttribute("success", "Add successfully");
        return "redirect:/customer";
    }



}
