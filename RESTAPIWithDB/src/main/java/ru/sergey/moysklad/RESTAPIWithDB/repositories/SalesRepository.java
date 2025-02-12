package ru.sergey.moysklad.RESTAPIWithDB.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sergey.moysklad.RESTAPIWithDB.models.Sale;

public interface SalesRepository extends JpaRepository<Sale, Long> {
}
