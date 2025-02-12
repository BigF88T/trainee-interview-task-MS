package ru.sergey.moysklad.RESTAPIWithDB.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class ProductDTO {

    @NotNull
    @Size(min = 2, max = 255, message = "Название товара должно быть больше 2 и меньше 255 символов")
    private String name;

    @Size(max = 4096, message = "Описание товара не должно превышать 4096 символов")
    private String description;

    @PositiveOrZero(message = "Цена должна быть больше или равна нулю")
    private Double price = 0.0;

    @PositiveOrZero(message = "Количество товара должно быть больше или равно нулю")
    private int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
