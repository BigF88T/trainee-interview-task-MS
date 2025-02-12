package ru.sergey.moysklad.RESTAPIWithDB.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sergey.moysklad.RESTAPIWithDB.models.Delivery;
import ru.sergey.moysklad.RESTAPIWithDB.models.Product;
import ru.sergey.moysklad.RESTAPIWithDB.repositories.DeliveriesRepository;
import ru.sergey.moysklad.RESTAPIWithDB.repositories.ProductsRepository;
import ru.sergey.moysklad.RESTAPIWithDB.util.ProductNotFoundException;
import ru.sergey.moysklad.RESTAPIWithDB.util.SortParameters;

import java.beans.Transient;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final DeliveriesRepository deliveriesRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository, DeliveriesRepository deliveriesRepository) {
        this.productsRepository = productsRepository;
        this.deliveriesRepository = deliveriesRepository;
    }


    public List<Product> findAll(int page, int prodPerPage, String sortParam, String directionParam) {
        if (sortParam != null && checkSortParam(sortParam)) {
            Sort.Direction direction = checkDirection(directionParam);
            return productsRepository.findAll(PageRequest.of(page, prodPerPage, Sort.by(direction, sortParam))).getContent();
        } else
            return productsRepository.findAll(PageRequest.of(page, prodPerPage)).getContent();
    }

    public List<Product> searchByName(int page, int prodPerPage, String sortParam, String directionParam, String query) {
        if (sortParam != null && checkSortParam(sortParam)) {
            Sort.Direction direction = checkDirection(directionParam);
            return productsRepository.findByNameContainsIgnoreCase(query, PageRequest.of(page, prodPerPage, Sort.by(direction, sortParam)));
        } else
            return productsRepository.findByNameContainsIgnoreCase(query, PageRequest.of(page, prodPerPage));
    }

    public List<Product> searchByPrice(int page, int prodPerPage, String sortParam, String directionParam, Double price, boolean isBefore ) {
        if (sortParam != null && checkSortParam(sortParam)) {
            Sort.Direction direction = checkDirection(directionParam);
            return isBefore ?
                    productsRepository.findByPriceBefore(price, PageRequest.of(page, prodPerPage, Sort.by(direction, sortParam)))
                    : productsRepository.findByPriceAfter(price, PageRequest.of(page, prodPerPage, Sort.by(direction, sortParam)));
        } else {
            return isBefore ?
                    productsRepository.findByPriceBefore(price, PageRequest.of(page, prodPerPage))
                    : productsRepository.findByPriceAfter(price, PageRequest.of(page, prodPerPage));
        }

    }

    public Product findOne(long id) {
        Optional<Product> foundProduct = productsRepository.findById(id);
        return foundProduct.orElseThrow(ProductNotFoundException::new);
    }

    @Transactional
    public void save(Product product) {
        productsRepository.save(product);
    }

    @Transactional
    public void update(long id, Product updatedProduct) {
        productsRepository.findById(id).orElseThrow(ProductNotFoundException::new) ;

        updatedProduct.setId(id);
        productsRepository.save(updatedProduct);
    }

    @Transactional
    public void delete(long id) {
        productsRepository.findById(id).orElseThrow(ProductNotFoundException::new) ;
        productsRepository.deleteById(id);
    }

    @Transient
    private boolean checkSortParam(String sortParam) {
        return Arrays.stream(SortParameters.values()).anyMatch(existParam -> existParam.getParam().equals(sortParam));
    }

    @Transient
    private Sort.Direction checkDirection(String directionParam) {
        if(directionParam.equals("down"))
            return Sort.Direction.DESC;
        else
            return Sort.Direction.ASC;
    }
}
