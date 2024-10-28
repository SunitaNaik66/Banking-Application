package com.myBank.service.ServiceImpl;

import com.myBank.Repository.AccountRepository;
import com.myBank.Repository.TransactionRepository;
import com.myBank.entity.Account;
import com.myBank.entity.Notification;
import com.myBank.service.AccountService;
import com.myBank.entity.Transaction;
import com.myBank.service.NotificationService;
import com.myBank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountServiceImpl implements AccountService {
   @Autowired
    private AccountRepository accountRepository;
   @Autowired
   private TransactionRepository transactionRepository;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private NotificationService notificationService;

    @Override
    public void transferFunds(Long fromAccountId, Long toAccountId, BigDecimal amount) {
       /* Account fromAccount = accountRepository.findById(fromAccountId).orElseThrow(() -> new RuntimeException("Account not found"));
        Account toAccount = accountRepository.findById(toAccountId).orElseThrow(() -> new RuntimeException("Account not found"));

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        // Calculate transaction fee for Current Account
        if ("Current".equals(fromAccount.getAccountType())) {
            BigDecimal fee = fromAccount.calculateTransactionFee(amount);
            fromAccount.setBalance(fromAccount.getBalance().subtract(amount.add(fee))); // Deduct amount + fee
        } else {
            fromAccount.setBalance(fromAccount.getBalance().subtract(amount)); // Deduct only the amount
        }

        toAccount.setBalance(toAccount.getBalance().add(amount)); // Credit the amount to the receiving account

        // Save both accounts
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);*/
        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        BigDecimal transactionFee = BigDecimal.ZERO;

        // Calculate transaction fee for Current Account
        if ("Current".equals(fromAccount.getAccountType())) {
            transactionFee = fromAccount.calculateTransactionFee(amount);
            fromAccount.setBalance(fromAccount.getBalance().subtract(amount.add(transactionFee)));
        } else {
            fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        }

        toAccount.setBalance(toAccount.getBalance().add(amount));

        // Save both accounts
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // Log the debit transaction for the sender
    Transaction debitTransaction=new Transaction();
        debitTransaction.setAccount(fromAccount);
        debitTransaction.setTransactionType("Debit");
        debitTransaction.setAmount(amount);
        debitTransaction.setTransactionFee(transactionFee);
        debitTransaction.setTimestamp(LocalDateTime.now());
        debitTransaction.setDescription("Transfer to account " + toAccountId);
        transactionService.logTransaction(debitTransaction); // Use TransactionService to log

        // Log the credit transaction for the receiver
        Transaction creditTransaction = new Transaction();
        creditTransaction.setAccount(toAccount);
        creditTransaction.setTransactionType("Credit");
        creditTransaction.setAmount(amount);
        creditTransaction.setTransactionFee(BigDecimal.ZERO);
        creditTransaction.setTimestamp(LocalDateTime.now());
        creditTransaction.setDescription("Received transfer from account " + fromAccountId);
        transactionRepository.save(creditTransaction);

        //Create and send notification for the sender
        Notification receiverNotification = new Notification();
        receiverNotification.setAccount(toAccount);
        receiverNotification.setMessage("Credited " + amount + ". New balance: " + toAccount.getBalance());
        receiverNotification.setTimestamp(LocalDateTime.now());
        notificationService.sendNotification(receiverNotification);
    }

    @Override
    public void applyInterest(Long savingsAccountId) {
        Account savingsAccount = accountRepository.findById(savingsAccountId)
                .orElseThrow(() -> new RuntimeException("Savings account not found"));

        if ("Savings".equals(savingsAccount.getAccountType())) {
            BigDecimal interest = savingsAccount.calculateInterest();
            savingsAccount.setBalance(savingsAccount.getBalance().add(interest)); // Add interest to the balance
            accountRepository.save(savingsAccount);
        }
    }



    @Override
    public void depositToSavingsAccount(Long savingsAccountId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Deposit amount must be greater than zero");
        }

        Account savingsAccount = accountRepository.findById(savingsAccountId)
                .orElseThrow(() -> new RuntimeException("Savings account not found"));

        // Update the balance
        savingsAccount.setBalance(savingsAccount.getBalance().add(amount));

        // Save the updated account
        accountRepository.save(savingsAccount);

        // Log the transaction for the deposit
        Transaction depositTransaction = new Transaction();
        depositTransaction.setAccount(savingsAccount);
        depositTransaction.setTransactionType("Credit");
        depositTransaction.setAmount(amount);
        depositTransaction.setTransactionFee(BigDecimal.ZERO); // Assuming no fee for deposits
        depositTransaction.setTimestamp(LocalDateTime.now());
        depositTransaction.setDescription("Deposit to Savings Account");

        transactionRepository.save(depositTransaction); // Log the transaction
    }
}

