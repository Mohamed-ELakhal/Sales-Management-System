package com.example.sales.management.system.dto;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;
@Data
public class SaleDto {

    private List<TransactionDto> transactions;
    @NotEmpty(message = "email can not be a null or empty")
    @Email(message = "Invalid email address")
    private String  sellerEmail;

    @NotEmpty(message = "email can not be a null or empty")
    @Email(message = "Invalid email address")
    private String clientEmail;

}
