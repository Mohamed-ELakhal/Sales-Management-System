package com.example.sales.management.system.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Data
public class ProductDto {

    @NotEmpty(message = "Name can not be a null or empty")
    private String name;
    @NotNull(message = "Quantity can not be a null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Quantity must be greater than zero")
    private double quantity;
    @NotNull(message = "Price can not be a null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private double price;
    @NotEmpty(message = "Category can not be a null or empty")
    private String category;
    @NotEmpty(message = "Description can not be a null or empty")
    private String description;
}
