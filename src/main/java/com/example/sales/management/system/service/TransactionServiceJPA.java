package com.example.sales.management.system.service;

import com.example.sales.management.system.dto.TransactionDto;
import com.example.sales.management.system.entity.Product;
import com.example.sales.management.system.entity.Transaction;
import com.example.sales.management.system.exception.NotEnoughQuantityException;
import com.example.sales.management.system.exception.ResourceNotFoundException;
import com.example.sales.management.system.repository.ProductRepository;
import com.example.sales.management.system.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class TransactionServiceJPA implements   TransactionService{
    private ProductRepository productRepository;
    private TransactionRepository transactionRepository;
    @Override
    public Transaction createTransaction(TransactionDto transactionDto) {
        Product product = productRepository.findByName(transactionDto.getProductName()).orElseThrow(
                () -> new ResourceNotFoundException("Product", "Name", transactionDto.getProductName())
        );
        if(transactionDto.getQuantity()>product.getQuantity())
            throw new NotEnoughQuantityException(product.getQuantity());

        Transaction transaction=new Transaction();
        double price=transactionDto.getQuantity()*product.getPrice();
        transaction.setQuantity(transactionDto.getQuantity());
        transaction.setPrice(price);
        product.setQuantity(product.getQuantity()-transactionDto.getQuantity());
        productRepository.save(product);
        product.addTransaction(transaction);
        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public List<Object[]> findTop3SellingProductsInDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return transactionRepository.findTop3SellingProductsInDateRange(startDate,endDate);
    }
}
