package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.CustomerType;
import com.codegym.service.CustomerService;
import com.codegym.service.CustomerTypeService;
import com.codegym.service.ICustomerService;
import com.codegym.service.ICustomerTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/customers")
public class CustomerController {
    public CustomerController(){
        super();
        System.out.println("khỏi tạo controller");
    }
    private final ICustomerService customerService = new CustomerService();
    private final ICustomerTypeService customerTypeService = new CustomerTypeService();
//    @GetMapping("")
//    public String index(Model model) {
//
//        List<Customer> customerList = customerService.findAll();
//        model.addAttribute("customers", customerList);
//        return "/index";
//    }

    // Cách sử dụng model Attributes
    @GetMapping("")
    public String index(Model model) {

//        List<Customer> customerList = customerService.findAll();
//        model.addAttribute("customers", customerList);
        return "/index";
    }

    // Định nghĩa 1 model @ModelAttribute("customers")
    @ModelAttribute("customers")
    public Iterable<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    @ModelAttribute("customerTypes")
    public Iterable<CustomerType> getAllCustomerTypes() {
        System.out.println("Tạo modelAttribute");
        return customerTypeService.findAll();
    }


    @GetMapping("/create")
    public String create(Model model) {
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
    public String save(@Validated @ModelAttribute(name = "customer") Customer customer, BindingResult bindingResult, Model model) {
        new Customer().validate(customer, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/create";
        }



        customer.setId((int) (Math.random() * 10000));
        customerService.save(customer);

        CustomerType customerType = customerTypeService.findById(customer.getCustomerType().getId());
        customer.setCustomerType(customerType);

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

        CustomerType customerType = customerTypeService.findById(customer.getCustomerType().getId());
        customer.setCustomerType(customerType);

        customerService.update(customer.getId(), customer);
        redirect.addFlashAttribute("success", "Add successfully");
        return "redirect:/customers";
    }



}
