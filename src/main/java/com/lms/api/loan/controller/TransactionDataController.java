package com.lms.api.loan.controller;

import com.lms.api.loan.dto.TransactionDataDTO;
import com.lms.api.loan.service.TransactionDataService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionDataController {

    private final TransactionDataService service;

    @Operation(summary = "Expose customer transactions to scoring engine")
    @GetMapping("/{customerNumber}")
    public ResponseEntity<List<TransactionDataDTO>> getTransactions(@PathVariable String customerNumber) {
        return ResponseEntity.ok(service.getTransactionData(customerNumber));
    }
}
