package com.xichen.thejavabank.controller;

import com.xichen.thejavabank.entity.Transaction;
import com.xichen.thejavabank.service.impl.BankStatement;
import com.xichen.thejavabank.service.impl.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bankStatement")
@AllArgsConstructor
public class TransactionController {
    private BankStatement bankStatement;
    @GetMapping
    public List<Transaction> generateBankStatement(@RequestParam String accountNumber,
                                                   @RequestParam String startDate,
                                                   @RequestParam String endDate) {
        bankStatement.generateBankStatementPDF(accountNumber, startDate, endDate);
        return bankStatement.generateStatement(accountNumber, startDate, endDate);
    }
}
