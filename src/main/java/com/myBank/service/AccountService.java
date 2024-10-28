package com.myBank.service;



import java.math.BigDecimal;

public interface AccountService {
    void transferFunds(Long fromAccountId, Long toAccountId, BigDecimal amount);
    void applyInterest(Long savingsAccountId);

    void depositToSavingsAccount(Long savingsAccountId, BigDecimal amount);

}
