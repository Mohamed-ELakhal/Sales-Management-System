package com.example.sales.management.system.service;

import com.example.sales.management.system.dto.AccountDto;
import com.example.sales.management.system.entity.Account;
import com.example.sales.management.system.exception.ResourceNotFoundException;
import com.example.sales.management.system.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceJPA implements AccountService{
    AccountRepository accountRepository;
    @Override
    public void CreateAccount(AccountDto accountDto) {
        Account account=new Account();
        account.setEmail(accountDto.getEmail());
        account.setPwd(accountDto.getPwd());
        accountRepository.save(account);
    }
}
