package com.xichen.thejavabank.service.impl;

import com.xichen.thejavabank.dto.TransactionDto;
import com.xichen.thejavabank.entity.Transaction;
import com.xichen.thejavabank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public void saveTransaction(TransactionDto transactionDto) {
        Transaction transaction = Transaction.builder()
                .accountNumber(transactionDto.getAccountNumber())
                .amount(transactionDto.getAmount())
                .status("SUCCESS")
                .transactionType(transactionDto.getTransactionType()
                ).build();
        transactionRepository.save(transaction);
        System.out.println("Transaction saved successfully");
    }
}
