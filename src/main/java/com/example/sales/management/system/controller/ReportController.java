package com.example.sales.management.system.controller;

import com.example.sales.management.system.dto.ClientReportDto;
import com.example.sales.management.system.dto.SaleReportDto;
import com.example.sales.management.system.service.ClientService;
import com.example.sales.management.system.service.SaleService;
import com.example.sales.management.system.service.TransactionService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(path="/api/Report", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@AllArgsConstructor
public class ReportController {
    private TransactionService transactionService;
    private ClientService clientService;
    private SaleService saleService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping("/sales")
    public ResponseEntity<SaleReportDto> generateSalesReport(@RequestParam("startDate") String startDate,
                                                                            @RequestParam("endDate") String endDate) {
        LocalDateTime start=LocalDateTime.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        LocalDateTime end=LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        SaleReportDto report = new SaleReportDto();
        report.setTopProducts(transactionService.findTop3SellingProductsInDateRange(start, end));
        report.setTotalNumberofSales(saleService.countSalesBetweenDates(start, end));
        report.setTotalRevenue(saleService.getTotalRevenueBetweenDates(start, end));
        report.setTopSeller(saleService.findTopSellersByRevenueBetweenDates(start, end));
        logger.info("Sale Report generated at {}: {}", getCurrentDateTime(), report);

        return ResponseEntity.status(HttpStatus.OK).body(report);

    }
    @GetMapping("/clients")
    public ResponseEntity<ClientReportDto> generateClientReport (){
        ClientReportDto clientReportDto =new ClientReportDto();
        clientReportDto.setTotalNumberOfClients(clientService.countClients());
        clientReportDto.setTopSpendingClients(clientService.findTopSpendingClientsWithTotalSpent());
        logger.info("Client Report generated at {}: {}", getCurrentDateTime(), clientReportDto);
        return ResponseEntity.status(HttpStatus.OK).body(clientReportDto);
    }

    private String getCurrentDateTime() {
        return LocalDateTime.now().format(dateTimeFormatter);
    }
}
