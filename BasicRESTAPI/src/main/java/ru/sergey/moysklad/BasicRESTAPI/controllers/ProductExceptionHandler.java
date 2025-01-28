package ru.sergey.moysklad.BasicRESTAPI.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.sergey.moysklad.BasicRESTAPI.util.ProductErrorResponse;
import ru.sergey.moysklad.BasicRESTAPI.util.ProductException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ProductErrorResponse> handleAllExceptions(Exception e) {
        ProductErrorResponse response = new ProductErrorResponse(
                e.getMessage(),
                LocalDateTime.now().toString()
        );

        // В HTTP ответе тело ответа (response) и статус в заголовке
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); // INTERNAL_SERVER_ERROR - 500
    }

    @ExceptionHandler(ProductException.class)
    private ResponseEntity<ProductErrorResponse> handleException(ProductException e) {
        ProductErrorResponse response = new ProductErrorResponse(
                e.getMessage(),
                LocalDateTime.now().toString()
        );

        // В HTTP ответе тело ответа (response) и статус в заголовке
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // NOT_FOUND - 404 статус
    }
}
