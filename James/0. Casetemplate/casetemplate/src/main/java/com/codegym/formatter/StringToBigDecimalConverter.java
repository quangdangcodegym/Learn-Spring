package com.codegym.formatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;

public class StringToBigDecimalConverter implements Converter<String, BigDecimal> {


    @Override
    public BigDecimal convert(String source) {
//        System.out.println("CHẠY HÀM CONVERT........." + source);
        BigDecimal bigDecimal = null;
        try {

            bigDecimal = new BigDecimal(source);
            bigDecimal = bigDecimal.add(new BigDecimal(1000));

//            System.out.println("CHẠY HÀM CONVERT........." + bigDecimal);
        } catch (NumberFormatException numberFormatException) {
//            System.out.println("CHẠY HÀM CONVERT......... VÀO EXCEPTION");
//            return new BigDecimal(500000);
            throw new IllegalArgumentException("invalid date format. Please use this pattern");
        }
        return bigDecimal;
    }

}
