package com.example.sales.management.system.service;

import com.example.sales.management.system.dto.TransactionDto;
import com.example.sales.management.system.entity.Transaction;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {
    Transaction createTransaction(TransactionDto transactionDto);
    List<Object[]> findTop3SellingProductsInDateRange(LocalDateTime startDate, LocalDateTime endDate);
}
