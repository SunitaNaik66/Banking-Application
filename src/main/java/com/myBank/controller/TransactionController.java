package com.myBank.controller;



import com.myBank.entity.Transaction;
import com.myBank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @PostMapping
    public ResponseEntity<String> logTransaction(@RequestBody Transaction transaction) {
        transactionService.logTransaction(transaction);
        return ResponseEntity.ok("Transaction logged successfully");
    }





}

