package com.codegym.config;

import com.codegym.formatter.StringToBigDecimalConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Mới sửa
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToBigDecimalConverter());
    }


    @Bean
    public ModelMapper getModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        /**
        if (modelMapper.getTypeMap(Product.class, ProductDTO.class) == null) {
            modelMapper.createTypeMap(Product.class, ProductDTO.class);
        }
        if(modelMapper.getTypeMap(CartItem.class, CartItemsDTO.class)==null){
            TypeMap<CartItem, CartItemsDTO> propertyMapper = modelMapper.createTypeMap(CartItem.class, CartItemsDTO.class);
            Converter<Product, ProductDTO> productConverter =
                    mappingContext -> mappingContext.getSource().toProductDTO();
            propertyMapper.addMappings(mapper->mapper.using(productConverter).map(CartItem::getProduct, CartItemsDTO::setProductDTO));
        }

        if(modelMapper.getTypeMap(Cart.class, CartDTO.class)==null){
            TypeMap<Cart, CartDTO> propertyMapper = modelMapper.createTypeMap(Cart.class, CartDTO.class);
            Converter<List<CartItem>, List<CartItemsDTO>> listConverter =
                    c -> c.getSource().stream().map(CartItem::toCartItemsDTO).collect(Collectors.toList());
            propertyMapper.addMappings(mapper->mapper.using(listConverter).map(Cart::getCartItems, CartDTO::setCartItemsDTOS));
        }
         **/
        return modelMapper;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}