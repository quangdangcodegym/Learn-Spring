package com.codegym.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;

public class Customer implements Validator {

    private int id;

    @NotEmpty
    @Size(min = 2, max = 30)
    private String name;
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email không hơp lệ")
    @NotBlank       // không được trống và ko được chứa khoảng trắng. Áp dụng cho String
    private String email;
    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{7,19}$", message = "Address không hợp lệ")
    @Size(min = 1, max = 20, message = "Địa chỉ phải từ 8-20 kí tự")
    @NotEmpty       // không được trống và ko được chứa khoảng trắng. Áp dụng cho List, String, Map
    private String address;

//    @Validated            để Validated là sai, vì validated chỉ kích hoạt validate ở Controller

    private CustomerType customerType;

    public Customer() {
//        this.customerType = new CustomerType();
    }

    public Customer(int id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public Customer(int id, String name, String email, String address, CustomerType customerType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.customerType = customerType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Customer.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;
        CustomerType customerType = customer.getCustomerType();
        if (customerType.getId() <= 0 || customerType.getId() > 10) {
            errors.rejectValue("customerType", null, "Loại khách hàng không hợp lệ");

        }

        /**
         PhoneNumber phoneNumber = (PhoneNumber) target;
         String number = phoneNumber.getNumber();
         ValidationUtils.rejectIfEmpty(errors, "number", "number.empty");       // có thể dùng lớp ValidationUtils để add thêm lỗi
         if (number.length()>11 || number.length()<10){
         errors.rejectValue("number", "number.length");         // hoặc dùng errors.rejectValue("number", "number.length");
         }
         if (!number.startsWith("0")){
         errors.rejectValue("number", "number.startsWith");
         }
         if (!number.matches("(^$|[0-9]*$)")){
         errors.rejectValue("number", "number.matches");
         }
         */
    }
}