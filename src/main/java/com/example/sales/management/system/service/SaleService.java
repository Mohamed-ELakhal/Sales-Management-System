package com.example.sales.management.system.service;

import com.example.sales.management.system.dto.SaleDto;
import com.example.sales.management.system.entity.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleService {
    void createSale(SaleDto saleDto);

    long countSalesBetweenDates(LocalDateTime startDate,LocalDateTime endDate);



    Double getTotalRevenueBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    List<Object[]> findTopSellersByRevenueBetweenDates(LocalDateTime startDate,LocalDateTime endDate);
}
