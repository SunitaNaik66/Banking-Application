package com.myBank.controller;

import com.myBank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(@RequestParam Long fromAccountId, @RequestParam Long toAccountId, @RequestParam BigDecimal amount) {
        try {
            accountService.transferFunds(fromAccountId, toAccountId, amount);
            return ResponseEntity.ok("Transfer successful");
        } catch (RuntimeException e) {
            return
                    ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{savingsAccountId}/apply-interest")
    public ResponseEntity<String> applyInterest(@PathVariable Long savingsAccountId) {
        try {
            accountService.applyInterest(savingsAccountId);
            return ResponseEntity.ok("Interest applied successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{savingsAccountId}/deposit")
    public ResponseEntity<String> depositToSavingsAccount(@PathVariable Long savingsAccountId,
                                                          @RequestParam BigDecimal amount) {
        try {
            accountService.depositToSavingsAccount(savingsAccountId, amount);
            return ResponseEntity.ok("Deposit successful");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}

