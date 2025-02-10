package ru.sergey.moysklad.RESTAPIWithDB.util;

public enum SortParameters {
    PRICE ("price"),
    NAME ("name"),
    STOCK ("inStock");

    private final String param;

    SortParameters(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }
}
