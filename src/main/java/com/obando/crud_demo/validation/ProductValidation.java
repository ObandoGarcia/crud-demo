package com.obando.crud_demo.validation;

import com.obando.crud_demo.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        if (product.getDescription() == null || product.getDescription().isBlank()){
            errors.rejectValue("description", "La descripcion no debe estar vacia");
        }
    }
}
