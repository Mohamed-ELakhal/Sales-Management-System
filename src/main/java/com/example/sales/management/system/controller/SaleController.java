package com.example.sales.management.system.controller;

import com.example.sales.management.system.constants.ProjectConstants;
import com.example.sales.management.system.dto.ProductDto;
import com.example.sales.management.system.dto.ResponseDto;
import com.example.sales.management.system.dto.SaleDto;
import com.example.sales.management.system.dto.TransactionDto;
import com.example.sales.management.system.entity.Sale;
import com.example.sales.management.system.entity.Seller;
import com.example.sales.management.system.exception.AlreadyExistsException;
import com.example.sales.management.system.repository.ClientRepository;
import com.example.sales.management.system.repository.SellerRepository;
import com.example.sales.management.system.service.ClientService;
import com.example.sales.management.system.service.SaleService;
import com.example.sales.management.system.service.SellerService;
import com.example.sales.management.system.service.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/Sale", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@AllArgsConstructor
public class SaleController {
    private SaleService saleService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody SaleDto saleDto) {
        if(saleDto.getTransactions().size()==0)
            throw new AlreadyExistsException("Transactions cannot be empty");
        saleService.createSale(saleDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(ProjectConstants.STATUS_201, ProjectConstants.MESSAGE_201));

    }
}
