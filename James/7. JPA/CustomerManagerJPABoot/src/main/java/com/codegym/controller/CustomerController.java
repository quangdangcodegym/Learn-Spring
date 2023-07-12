package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.CustomerType;
import com.codegym.repository.CustomerRepository;
import com.codegym.service.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICustomerTypeService customerTypeService;
    @Autowired
    private HttpSession httpSession;

    // Cách sử dụng model Attributes
    @GetMapping("")
    public String index(Model model, HttpServletRequest request) {

        List<Customer> list = customerService.findAll();
        list.stream().forEach(customer -> System.out.println(customer.getCustomerType().getName()));
        model.addAttribute("customers", list);

        RequestContextHolder.currentRequestAttributes().getSessionId();
        readCookies(request.getCookies());
        return "views/index";
    }

    @ModelAttribute("customerTypes")
    public Iterable<CustomerType> getAllCustomerTypes() {
        System.out.println("Tạo modelAttribute");
        List<CustomerType> customerTypes = customerTypeService.findAll();
        return customerTypes;
    }


    @GetMapping("/create")
    public String create(Model model, HttpServletRequest request) {
        readCookies(request.getCookies());
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
    public String edit(@PathVariable Long id, Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("customer", customerService.findById(id));


        RequestContextHolder.currentRequestAttributes().getSessionId();
        readCookies(request.getCookies());

        Cookie cookie = new Cookie("token-id", RequestContextHolder.currentRequestAttributes().getSessionId());
        cookie.setMaxAge(60*5); // expires in 7 days
        cookie.setPath("/");
        response.addCookie(cookie);
        return "views/edit";
    }
    public void readCookies(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie c : cookies) {
                System.out.println(c.getName() + " -- " + c.getValue());
            }
        }

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
