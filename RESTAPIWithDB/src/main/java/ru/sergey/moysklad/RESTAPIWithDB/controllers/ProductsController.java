package ru.sergey.moysklad.RESTAPIWithDB.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.sergey.moysklad.RESTAPIWithDB.dto.ProductDTO;
import ru.sergey.moysklad.RESTAPIWithDB.models.Product;
import ru.sergey.moysklad.RESTAPIWithDB.services.ProductsService;
import ru.sergey.moysklad.RESTAPIWithDB.util.ProductNotCreatedException;
import ru.sergey.moysklad.RESTAPIWithDB.util.ProductNotFoundException;
import ru.sergey.moysklad.RESTAPIWithDB.util.ProductsErrorResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ModelMapper modelMapper;
    private final ProductsService productsService;

    public ProductsController(ModelMapper modelMapper, ProductsService productsService) {
        this.modelMapper = modelMapper;
        this.productsService = productsService;
    }

    @GetMapping()
    public List<ProductDTO> getProducts() {
        return productsService.findAll().stream().map(this::convertToProductDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable("id")int id) {
        return convertToProductDTO(productsService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid ProductDTO productDTO,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            returnError(bindingResult);

        productsService.save(convertToProduct(productDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id,
                                             @RequestBody @Valid ProductDTO productDTO,
                                             BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            returnError(bindingResult);

        productsService.update(id, convertToProduct(productDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        productsService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ProductsErrorResponse> handleException(ProductNotFoundException e) {
        ProductsErrorResponse response = new ProductsErrorResponse(
                "Товар с таким id не найден",
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // NOT_FOUND - 404 статус
    }

    @ExceptionHandler
    private ResponseEntity<ProductsErrorResponse> handleException(ProductNotCreatedException e) {
        ProductsErrorResponse response = new ProductsErrorResponse(
                e.getMessage(),
                LocalDateTime.now().toString()
        );

        // В HTTP ответе тело ответа (response) и статус в заголовке
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // BAD_REQUEST - 400 статус
    }

    private Product convertToProduct(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }

    private ProductDTO convertToProductDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    private void returnError(BindingResult bindingResult) {
        StringBuilder errorMsg = new StringBuilder();

        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            errorMsg.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append(";");
            // view: Error field - Default message;
        }
        throw new ProductNotCreatedException(errorMsg.toString());
    }

}
