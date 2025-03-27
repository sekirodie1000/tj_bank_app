package com.xichen.thejavabank.repository;

import com.xichen.thejavabank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
