package ru.sergey.moysklad.RESTAPIWithDB.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Size(min = 2, max = 255, message = "Название товара должно быть больше 2 и меньше 255 символов")
    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Min(value = 1, message = "Количество товара в поставке должно быть больше нуля")
    @Column(name = "quantity")
    private int quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Delivery() {
    }

    public Delivery(String title, Product product, int quantity) {
        this.title = title;
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
