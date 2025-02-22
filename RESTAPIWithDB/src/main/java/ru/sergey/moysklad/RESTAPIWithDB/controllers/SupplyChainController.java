package ru.sergey.moysklad.RESTAPIWithDB.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.sergey.moysklad.RESTAPIWithDB.dto.DeliveryDTO;
import ru.sergey.moysklad.RESTAPIWithDB.dto.SaleDTORequest;
import ru.sergey.moysklad.RESTAPIWithDB.kafka.KafkaProducer;
import ru.sergey.moysklad.RESTAPIWithDB.services.DeliveriesServices;
import ru.sergey.moysklad.RESTAPIWithDB.services.SalesService;
import ru.sergey.moysklad.RESTAPIWithDB.util.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/commerce")
public class SupplyChainController {
    private final DeliveriesServices deliveriesServices;
    private final SalesService salesService;
    private final KafkaProducer kafkaProducer;

    @Autowired
    public SupplyChainController(DeliveriesServices deliveriesServices, SalesService salesService, KafkaProducer kafkaProducer) {
        this.deliveriesServices = deliveriesServices;
        this.salesService = salesService;
        this.kafkaProducer = kafkaProducer;
    }


    @PostMapping("/delivery")
    public ResponseEntity<HttpStatus> newDelivery(@RequestBody @Valid DeliveryDTO deliveryDTO,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            returnError(bindingResult);


        deliveriesServices.makeDelivery(deliveriesServices.convertToDelivery(deliveryDTO));

        kafkaProducer.sendMessage("New delivery: " + deliveryDTO.getTitle());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/sale")
    public ResponseEntity<HttpStatus> newSale(@RequestBody @Valid SaleDTORequest saleDTORequest,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            returnError(bindingResult);

        salesService.makeSale(salesService.convertToSale(saleDTORequest));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<TradeErrorResponse> handleException(ProductNotFoundException e) {
        TradeErrorResponse response = new TradeErrorResponse(
                "Товар с таким id не найден",
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // NOT_FOUND - 404 статус
    }

    @ExceptionHandler
    private ResponseEntity<TradeErrorResponse> handleException(ProductNotEnoughException e) {
        TradeErrorResponse response = new TradeErrorResponse(
                "Недостаточное количество товара",
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    private ResponseEntity<TradeErrorResponse> handleException(DeliveryNotCreatedException e) {
        TradeErrorResponse response = new TradeErrorResponse(
                e.getMessage(),
                LocalDateTime.now().toString()
        );
        // В HTTP ответе тело ответа (response) и статус в заголовке
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // BAD_REQUEST - 400 статус
    }

    @ExceptionHandler
    private ResponseEntity<TradeErrorResponse> handleException(SaleNotCreatedException e) {
        TradeErrorResponse response = new TradeErrorResponse(
                e.getMessage(),
                LocalDateTime.now().toString()
        );
        // В HTTP ответе тело ответа (response) и статус в заголовке
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // BAD_REQUEST - 400 статус
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
        throw new DeliveryNotCreatedException(errorMsg.toString());
    }
}
