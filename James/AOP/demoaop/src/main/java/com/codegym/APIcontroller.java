package com.codegym;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@RestController
@RequestMapping("/api/a")
public class APIcontroller {

    @GetMapping("")
    public String check() throws MyException2,MyException,ex3{
        int c = 0;

        if (c == 5) {
            throw new MyException();
        }else if(c == 0){
            throw new ex3();
        }
        return "HElloo";
    }
//    @ExceptionHandler(MyException.class)
//    public ModelAndView showInputNotAcceptable() {
//        return new ModelAndView("exception");
//    }
//
//    @ExceptionHandler(MyException2.class)
//    public ResponseEntity<?> showInputNotAcceptable2() {
//        return  new ResponseEntity<>("ÂDVASDVAV", HttpStatus.CONFLICT);
//    }

    @PostMapping("")
    public String check2(@Valid @RequestBody Student student, BindingResult bindingResult) throws MyException2,MyException,ex3{
        int c = 0;

//        if (c == 5) {
//            throw new MyException();
//        }else if(c == 0){
//            throw new ex3();
//        }
        return "HElloo";
    }
}
