package com.example.sales.management.system.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
@Data
public class AuthoritiesDto {
    List<String> authorities;
    @NotNull(message = "id can not be a null")
    int id;
}
