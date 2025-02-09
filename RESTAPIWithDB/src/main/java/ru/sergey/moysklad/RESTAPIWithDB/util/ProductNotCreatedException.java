package ru.sergey.moysklad.RESTAPIWithDB.util;

public class ProductNotCreatedException extends RuntimeException {
    public ProductNotCreatedException(String message) {
        super(message);
    }
}
