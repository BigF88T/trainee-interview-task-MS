package ru.sergey.moysklad.RESTAPIWithDB.util;

public class DeliveryNotCreatedException extends RuntimeException {

    public DeliveryNotCreatedException(String message) {
        super(message);
    }
}
