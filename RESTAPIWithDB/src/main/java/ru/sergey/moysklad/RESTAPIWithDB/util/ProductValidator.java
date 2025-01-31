package ru.sergey.moysklad.RESTAPIWithDB.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.sergey.moysklad.RESTAPIWithDB.models.Product;
import ru.sergey.moysklad.RESTAPIWithDB.services.ProductsService;

@Component
public class ProductValidator implements Validator {

    private final ProductsService productsService;

    @Autowired
    public ProductValidator(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
