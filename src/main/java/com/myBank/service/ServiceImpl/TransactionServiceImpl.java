package com.myBank.service.ServiceImpl;



import com.myBank.Repository.TransactionRepository;
import com.myBank.entity.Transaction;
import com.myBank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void logTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }


    }

