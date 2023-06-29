package com.codegym.springgreeting.controller;
import com.codegym.springgreeting.controller.create_bean.Student;
import com.codegym.springgreeting.controller.ioc_demo.Person;
import com.codegym.springgreeting.controller.old.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.XmlWebApplicationContext;

@Controller
public class GreetingController {
    @Autowired
    ApplicationContext applicationContext;
    @GetMapping("/greeting")
    public String greeting(@RequestParam String name, Model model) {

            // Cách 1: định nghĩa 1 bean trong appliaction content
//        Person p1 = (Person) applicationContext.getBean("p");
//        System.out.println(p1.getName());

        Student p2 = (Student) applicationContext.getBean("studentBean");
        System.out.println(p2.getName());

        model.addAttribute("name", name);
        return "index";
    }

}