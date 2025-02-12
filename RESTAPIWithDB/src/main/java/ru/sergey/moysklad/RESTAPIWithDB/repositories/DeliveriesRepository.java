package ru.sergey.moysklad.RESTAPIWithDB.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sergey.moysklad.RESTAPIWithDB.models.Delivery;

@Repository
public interface DeliveriesRepository extends JpaRepository<Delivery, Long> {
}
