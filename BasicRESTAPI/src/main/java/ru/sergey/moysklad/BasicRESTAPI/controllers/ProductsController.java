package ru.sergey.moysklad.BasicRESTAPI.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sergey.moysklad.BasicRESTAPI.dao.ProductDAO;
import ru.sergey.moysklad.BasicRESTAPI.dto.ProductDTO;
import ru.sergey.moysklad.BasicRESTAPI.models.Product;
import ru.sergey.moysklad.BasicRESTAPI.util.ProductErrorResponse;
import ru.sergey.moysklad.BasicRESTAPI.util.ProductException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.sergey.moysklad.BasicRESTAPI.util.ErrorsUtil.returnErrors;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductDAO productDAO;
    private final ModelMapper modelMapper;


    @Autowired
    public ProductsController(ProductDAO productDAO, ModelMapper modelMapper) {
        this.productDAO = productDAO;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<ProductDTO> getProducts() {
        return productDAO.index().stream().map(this::convertToProductDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable("id") int id) {
        if(productDAO.show(id).isPresent())
            return convertToProductDTO(productDAO.show(id).get());
        else
            throw new ProductException("Продукт с таким id не найден");
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid ProductDTO productDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            returnErrors(bindingResult);
        }

        productDAO.save(convertToProduct(productDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid ProductDTO productDTO,
                                             BindingResult bindingResult,
                                             @PathVariable("id") int id) {
        if(productDAO.show(id).isEmpty()){
            throw new ProductException("Продукт с id: " + id + " отсутствует");
        }
        if(bindingResult.hasErrors())
            returnErrors(bindingResult);

        productDAO.update(id, convertToProduct(productDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        if(productDAO.show(id).isEmpty()){
            throw new ProductException("Продукт с id: " + id + " отсутствует");
        }
        productDAO.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    private Product convertToProduct(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }

    private ProductDTO convertToProductDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }
}
