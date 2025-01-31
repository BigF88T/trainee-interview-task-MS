package ru.sergey.moysklad.RESTAPIWithDB.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sergey.moysklad.RESTAPIWithDB.models.Product;
import ru.sergey.moysklad.RESTAPIWithDB.repositories.ProductsRepository;
import ru.sergey.moysklad.RESTAPIWithDB.util.ProductNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductsService {
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    public Product findOne(int id) {
        Optional<Product> foundProduct = productsRepository.findById(id);
        return foundProduct.orElseThrow(ProductNotFoundException::new);
    }

    @Transactional
    public void save(Product product) {
        productsRepository.save(product);
    }

    @Transactional
    public void update(int id, Product updatedProduct) {
        productsRepository.findById(id).orElseThrow(ProductNotFoundException::new) ;

        updatedProduct.setId(id);
        productsRepository.save(updatedProduct);
    }

    @Transactional
    public void delete(int id) {
        productsRepository.findById(id).orElseThrow(ProductNotFoundException::new) ;
        productsRepository.deleteById(id);
    }

}
