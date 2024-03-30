package com.example.sales.management.system.dto;

import com.example.sales.management.system.CutomValidator.ValidPassword;
import com.example.sales.management.system.entity.Authority;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class AccountDto {
    @Column(name="email")
    @NotEmpty(message = "email can not be a null or empty")
    @Email(message = "Invalid email address")
    private String email;
    @ValidPassword(message = "Password must be 8 characters length and contain at least one upper letter, one special character, and one lower letter")
    private String pwd;
}
