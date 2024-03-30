package com.example.sales.management.system.controller;

import com.example.sales.management.system.constants.ProjectConstants;
import com.example.sales.management.system.dto.AccountDto;
import com.example.sales.management.system.dto.ProductDto;
import com.example.sales.management.system.dto.ResponseDto;
import com.example.sales.management.system.service.AccountService;
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
@RequestMapping(path="/api/Account", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@AllArgsConstructor
public class AccountController {
    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody AccountDto accountDto) {
        accountService.CreateAccount(accountDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(ProjectConstants.STATUS_201, ProjectConstants.MESSAGE_201));
    }
}
