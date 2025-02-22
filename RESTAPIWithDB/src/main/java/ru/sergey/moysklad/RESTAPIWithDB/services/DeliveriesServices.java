package ru.sergey.moysklad.RESTAPIWithDB.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sergey.moysklad.RESTAPIWithDB.dto.DeliveryDTO;
import ru.sergey.moysklad.RESTAPIWithDB.models.Delivery;
import ru.sergey.moysklad.RESTAPIWithDB.models.Product;
import ru.sergey.moysklad.RESTAPIWithDB.repositories.DeliveriesRepository;
import ru.sergey.moysklad.RESTAPIWithDB.repositories.ProductsRepository;
import ru.sergey.moysklad.RESTAPIWithDB.util.ProductNotFoundException;

@Service
public class DeliveriesServices {

    private final DeliveriesRepository deliveriesRepository;
    private final ProductsRepository productsRepository;

    @Autowired
    public DeliveriesServices(DeliveriesRepository deliveriesRepository, ProductsRepository productsRepository) {
        this.deliveriesRepository = deliveriesRepository;
        this.productsRepository = productsRepository;
    }

    @Transactional
    public void makeDelivery(Delivery delivery) {
        Product product = productsRepository.findById(delivery.getProduct().getId())
                .orElseThrow(ProductNotFoundException::new);

        product.setQuantity(product.getQuantity() + delivery.getQuantity());

        if (!product.getInStock())
            product.setInStock(true);

        productsRepository.save(product);
        deliveriesRepository.save(delivery);
    }

    public Delivery convertToDelivery(DeliveryDTO deliveryDTO) {
        long productId = deliveryDTO.getProductId();
        Delivery delivery = new Delivery();
        delivery.setProduct(productsRepository.findById(productId).orElseThrow(ProductNotFoundException::new));
        delivery.setTitle(deliveryDTO.getTitle());
        delivery.setQuantity(deliveryDTO.getQuantity());

        return delivery;
    }

    public DeliveryDTO covertToDeliveryDTORequest(Delivery delivery) {
        long productId = delivery.getProduct().getId();
        DeliveryDTO deliveryDTO = new DeliveryDTO();
        deliveryDTO.setTitle(delivery.getTitle());
        deliveryDTO.setProductId(productId);
        deliveryDTO.setQuantity(delivery.getQuantity());

        return deliveryDTO;
    }
}
