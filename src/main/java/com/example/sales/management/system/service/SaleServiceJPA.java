package com.example.sales.management.system.service;

import com.example.sales.management.system.dto.SaleDto;
import com.example.sales.management.system.dto.TransactionDto;
import com.example.sales.management.system.entity.Client;
import com.example.sales.management.system.entity.Sale;
import com.example.sales.management.system.entity.Seller;
import com.example.sales.management.system.entity.Transaction;
import com.example.sales.management.system.exception.ResourceNotFoundException;
import com.example.sales.management.system.repository.ClientRepository;
import com.example.sales.management.system.repository.SaleRepository;
import com.example.sales.management.system.repository.SellerRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class SaleServiceJPA implements  SaleService{
    private SaleRepository saleRepository;
    private SellerRepository sellerRepository;
    private TransactionService transactionService;
    private ClientRepository clientRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Override
    public void createSale(SaleDto saleDto) {
        Sale sale=new Sale();
        saleRepository.save(sale);
        for(TransactionDto transactionDto:saleDto.getTransactions()) {
            Transaction transaction=transactionService.createTransaction(transactionDto);
            sale.addTransaction(transaction);
            logger.info("Transaction Added to sale at {}: {}",getCurrentDateTime(),transaction);
        }
        Seller seller=sellerRepository.findByEmail(saleDto.getSellerEmail()).orElseThrow(
                () -> new ResourceNotFoundException("Seller", "Email", saleDto.getSellerEmail()));
        Client client=clientRepository.findByEmail(saleDto.getClientEmail()).orElseThrow(
                () -> new ResourceNotFoundException("Client", "Email", saleDto.getClientEmail()));

        client.addSale(sale);
        seller.addSale(sale);
        logger.info("client Added at {}: {}", getCurrentDateTime(), client);
        logger.info("seller Added at {}: {}", getCurrentDateTime(), seller);
        saleRepository.save(sale);
        logger.info("Sale Saved at {}: {}", getCurrentDateTime(), sale);
    }

    @Override
    public long countSalesBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return saleRepository.countSalesBetweenDates(startDate,endDate);
    }

    @Override
    public Double getTotalRevenueBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return saleRepository.getTotalRevenueBetweenDates(startDate,endDate);
    }


    @Override
    public List<Object[]> findTopSellersByRevenueBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return saleRepository.findTopSellersByRevenueBetweenDates(startDate,endDate);
    }
    private String getCurrentDateTime() {
        return LocalDateTime.now().format(dateTimeFormatter);
    }
}
