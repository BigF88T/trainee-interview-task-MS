package ru.sergey.moysklad.RESTAPIWithDB.repositories;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sergey.moysklad.RESTAPIWithDB.models.Product;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
    @Query
    List<Product> findByNameContainsIgnoreCase(String query, PageRequest pageRequest);

    List<Product> findByPriceAfter(double price, PageRequest pageRequest);
    List<Product> findByPriceBefore(double price, PageRequest pageRequest);

    List<Product> findByInStockTrue();
}
