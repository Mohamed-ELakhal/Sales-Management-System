package com.example.sales.management.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotEnoughQuantityException extends RuntimeException {
    public NotEnoughQuantityException(double quantity){
        super(String.format("Not Enough Product Quantity in Inventory , available quantity %s  ",quantity));
    }
}
