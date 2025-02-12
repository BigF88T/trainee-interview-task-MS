package ru.sergey.moysklad.RESTAPIWithDB.util;

public class SaleNotCreatedException extends RuntimeException {
    public SaleNotCreatedException(String message) {
        super(message);
    }
}
