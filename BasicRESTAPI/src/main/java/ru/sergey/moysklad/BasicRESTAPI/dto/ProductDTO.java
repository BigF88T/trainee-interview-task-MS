package ru.sergey.moysklad.BasicRESTAPI.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class ProductDTO {

    @NotEmpty(message = "Название товара не должно быть пустым")
    @Size(min = 2, max = 255, message = "Название товара должно быть больше 2 и меньше 255 символов")
    private String name;

    @Size(max = 4096, message = "Описание товара не должно превышать 4096 символов")
    private String description;

    @PositiveOrZero(message = "Цена должна быть больше нуля")
    private Double price = 0.0d;

    private Boolean inStock = false;

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

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }
}
