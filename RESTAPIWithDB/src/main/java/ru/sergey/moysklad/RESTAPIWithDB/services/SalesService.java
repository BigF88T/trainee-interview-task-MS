package ru.sergey.moysklad.RESTAPIWithDB.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sergey.moysklad.RESTAPIWithDB.dto.SaleDTORequest;
import ru.sergey.moysklad.RESTAPIWithDB.models.Product;
import ru.sergey.moysklad.RESTAPIWithDB.models.Sale;
import ru.sergey.moysklad.RESTAPIWithDB.repositories.ProductsRepository;
import ru.sergey.moysklad.RESTAPIWithDB.repositories.SalesRepository;
import ru.sergey.moysklad.RESTAPIWithDB.util.ProductNotEnoughException;
import ru.sergey.moysklad.RESTAPIWithDB.util.ProductNotFoundException;

@Service
public class SalesService {
    private final ProductsRepository productsRepository;
    private final SalesRepository salesRepository;

    @Autowired
    public SalesService(ProductsRepository productsRepository, SalesRepository salesRepository) {
        this.productsRepository = productsRepository;
        this.salesRepository = salesRepository;
    }

    @Transactional
    public void makeSale(Sale sale) {
        Product product = productsRepository.findById(sale.getProduct().getId())
                .orElseThrow(ProductNotFoundException::new);

        if(product.getQuantity() < sale.getQuantity())
            throw new ProductNotEnoughException();

        product.setQuantity(product.getQuantity() - sale.getQuantity());

        if (product.getQuantity() == 0)
            product.setInStock(false);

        productsRepository.save(product);
        salesRepository.save(sale);
    }

    public Sale convertToSale(SaleDTORequest saleDTORequest) {
        Long productId = saleDTORequest.getProductId();
        Sale sale = new Sale();
        sale.setProduct(productsRepository.findById(productId).orElseThrow(ProductNotFoundException::new));
        sale.setTitle(saleDTORequest.getTitle());
        sale.setQuantity(saleDTORequest.getQuantity());

        return sale;
    }

    public SaleDTORequest convertToSaleDTORequest(Sale sale) {
        Long productId = sale.getProduct().getId();
        SaleDTORequest saleDTORequest = new SaleDTORequest();
        saleDTORequest.setTitle(saleDTORequest.getTitle());
        saleDTORequest.setProductId(productId);
        saleDTORequest.setQuantity(sale.getQuantity());

        return saleDTORequest;
    }
}
