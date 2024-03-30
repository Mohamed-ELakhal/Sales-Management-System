package com.example.sales.management.system.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionDto {
    @NotNull(message = "Quantity can not be a null")
    @DecimalMin(value = "0.0", inclusive = false, message = "quantity must be greater than zero")
    private double quantity;
    @NotEmpty(message = "Name can not be a null or empty")
    private String productName;
}
