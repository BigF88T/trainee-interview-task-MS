package ru.sergey.moysklad.BasicRESTAPI.models;

import jakarta.validation.constraints.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

public class Product {

    private long id;

    @NotEmpty(message = "Название товара не должно быть пустым")
    @Size(min = 2, max = 255, message = "Название товара должно быть больше 2 и меньше 255 символов")
    private String name;

    @Size(max = 4096, message = "Описание товара не должно превышать 4096 символов")
    private String description;

    @PositiveOrZero(message = "Цена должна быть больше или равна нулю")
    private Double price = 0.0d;
    
    private Boolean inStock = false;

    public Product() {}

    public Product(long id, String name, String description, Double price, Boolean inStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.inStock = inStock;
    }

    public Product(long id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Boolean isInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    @Override
    public String toString() {
        return "Product: " +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", description = '" + description + '\'' +
                ", price = " + price +
                ", inStock = " + inStock +
                '\n';
    }
}
