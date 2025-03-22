package com.lms.api.loan.controller;

import com.lms.api.loan.dto.LoanStatusResponse;
import com.lms.api.loan.model.LoanRequest;
import com.lms.api.loan.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/loan")
public class LoanController {

    private final LoanService service;

    @Operation(summary = "Request a loan")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Loan request accepted"),
            @ApiResponse(responseCode = "400", description = "Invalid request or customer not found"),
            @ApiResponse(responseCode = "409", description = "Customer has active loan")
    })
    @PostMapping("/request/{customerNumber}/{amount}")
    public ResponseEntity<LoanRequest> requestLoan(@PathVariable String customerNumber,
                                                   @PathVariable Double amount) {
        return ResponseEntity.ok(service.requestLoan(customerNumber, amount));
    }

    @Operation(summary = "Get loan status by customer number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Loan status retrieved"),
            @ApiResponse(responseCode = "404", description = "No loan request found for customer")
    })
    @GetMapping("/status/{customerNumber}")
    public ResponseEntity<LoanStatusResponse> getLoanStatus(
            @PathVariable String customerNumber) {
        return ResponseEntity.ok(service.getLoanStatus(customerNumber));
    }


}
