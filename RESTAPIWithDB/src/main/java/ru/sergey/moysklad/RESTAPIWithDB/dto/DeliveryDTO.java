package ru.sergey.moysklad.RESTAPIWithDB.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DeliveryDTO {

    @NotNull
    @Size(min = 2, max = 255, message = "Название товара должно быть больше 2 и меньше 255 символов")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private Long productId;

    @Min(value = 1, message = "Количество товара в поставке должно быть больше нуля")
    private int quantity;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
