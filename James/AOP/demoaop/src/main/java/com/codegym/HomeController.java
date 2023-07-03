package com.codegym;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping("")
    public String index() throws MyException,MyException2 {

        int c = 0;
        if (c == 5) {
            throw new MyException();
        }else if(c == 0){
            throw new MyException2();
        }
        return "index";
    }
    @ExceptionHandler(MyException.class)
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("exception");
    }

    @ExceptionHandler(MyException2.class)
    public ModelAndView showInputNotAcceptable2() {
        return new ModelAndView("ex2");
    }



}
