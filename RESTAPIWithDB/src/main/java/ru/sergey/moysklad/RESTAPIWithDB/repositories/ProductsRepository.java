package ru.sergey.moysklad.RESTAPIWithDB.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sergey.moysklad.RESTAPIWithDB.models.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

}
