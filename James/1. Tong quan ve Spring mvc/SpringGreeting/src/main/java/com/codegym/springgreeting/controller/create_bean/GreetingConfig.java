package com.codegym.springgreeting.controller.create_bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GreetingConfig {
    @Bean
// Here the method name is the
// bean id/bean name
    public Student studentBean(){
        // Return the College object
        return new Student(100, "Ly Ly");
    }
}
