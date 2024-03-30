package com.example.sales.management.system.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ClientDto {
    @NotEmpty(message = "Name can not be a null or empty")
    private String name;

    @NotEmpty(message = "lastName can not be a null or empty")
    private String lastName;

    @NotEmpty(message = "address can not be a null or empty")
    private String address;

    @NotEmpty(message = "mobile can not be a null or empty")
    private String mobile;
    @Column(name="email")
    @NotEmpty(message = "email can not be a null or empty")
    @Email(message = "Invalid email address")
    private String email;
}
