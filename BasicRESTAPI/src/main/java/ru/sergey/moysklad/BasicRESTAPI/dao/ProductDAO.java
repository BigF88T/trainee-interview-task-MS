package ru.sergey.moysklad.BasicRESTAPI.dao;

import org.springframework.stereotype.Component;
import ru.sergey.moysklad.BasicRESTAPI.models.Product;

import java.util.*;

@Component
public class ProductDAO {

    protected static List<Product> products;
    private static long id = 1;

    static {
        products = new ArrayList<>();

        Collections.addAll(products, new Product(id++, "Книга", "Немного описания для книги, чтобы было примерное понимание", 3000.0, true));
        Collections.addAll(products, new Product(id++, "Книга2", "Немного описания для книги2, чтобы было примерное понимание", 1230.0));
        Collections.addAll(products, new Product(id++, "Книга3", "Немного описания для книги3, чтобы было примерное понимание", 9120.0, true));
        Collections.addAll(products, new Product(id++, "Книга4", "Немного описания для книги4, чтобы было примерное понимание", 500.0, false));
        Collections.addAll(products, new Product(id++, "Ручка", "Все пытаются её продать", 500.0, true));
        Collections.addAll(products, new Product(id++, "Кружка", "Можно попить из неё или кинуть в кого-нибудь", 698.0, true));
    }

    public List<Product> index() {
        return products;
    }

    public Optional<Product> show(int id) {
        return products.stream().filter( prod -> prod.getId() == id).findFirst();
    }

    public void save(Product product) {
        product.setId(id++);
        products.add(product);
        System.out.println(products);
    }

    public void update(int id, Product updatedProduct) {
        updatedProduct.setId(id);
        if(show(id).isEmpty()) {

        }
        products.set(products.indexOf(show(id).get()), updatedProduct);
        System.out.println(products);
    }

    public void delete(int id) {
        Product deletedProd = products.stream().filter(prod -> prod.getId()==id).findFirst().get();
        products.remove(deletedProd);
        System.out.println(products);
    }
}
