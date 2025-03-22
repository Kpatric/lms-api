package com.lms.api.loan.controller;

import com.lms.api.loan.model.Customer;
import com.lms.api.loan.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@Tag(name = "Customer Management", description = "APIs for managing customer subscriptions")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @Operation(
            summary = "Subscribe a customer",
            description = "Registers a customer by customer number. Fails if customer already exists."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer successfully subscribed"),
            @ApiResponse(responseCode = "409", description = "Customer already exists"),
            @ApiResponse(responseCode = "400", description = "Invalid customer number")
    })
    @PostMapping("/{customerNumber}")
    public ResponseEntity<Customer> subscribe(
            @PathVariable String customerNumber) {
        service.subscribeCustomer(customerNumber);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
