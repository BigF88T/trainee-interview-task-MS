package ru.sergey.moysklad.BasicRESTAPI.util;

public class ProductErrorResponse {
    private String message;
    private String dateTime;

    public ProductErrorResponse(String message, String dateTime) {
        this.message = message;
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
