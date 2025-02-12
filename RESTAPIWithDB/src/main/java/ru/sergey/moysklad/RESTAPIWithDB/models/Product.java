package ru.sergey.moysklad.RESTAPIWithDB.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull
    @Size(min = 2, max = 255, message = "Название товара должно быть больше 2 и меньше 255 символов")
    @Column(name = "name")
    private String name;

    @Size(max = 4096, message = "Описание товара не должно превышать 4096 символов")
    @Column(name = "description")
    private String description;

    @PositiveOrZero(message = "Цена должна быть больше или равна нулю")
    @Column(name = "price")
    private Double price = 0.0;

    @Column(name = "in_stock")
    private Boolean inStock = false;

    @PositiveOrZero(message = "Количество товара должно быть больше или равно нулю")
    @Column(name = "stock_quantity")
    private int quantity;

    public Product() {}

    public Product(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(String name, String description, Double price, Boolean inStock, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.inStock = inStock;
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

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", inStock=" + inStock +
                '}';
    }
}
