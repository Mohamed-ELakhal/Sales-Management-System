package com.example.sales.management.system.controller;

import com.example.sales.management.system.constants.ProjectConstants;
import com.example.sales.management.system.dto.ClientDto;
import com.example.sales.management.system.dto.ProductDto;
import com.example.sales.management.system.dto.ResponseDto;
import com.example.sales.management.system.entity.Client;
import com.example.sales.management.system.entity.Product;
import com.example.sales.management.system.service.ClientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/Client", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@AllArgsConstructor
public class ClientController {
    private ClientService clientService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody ClientDto clientDto) {
        clientService.createClient(clientDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(ProjectConstants.STATUS_201, ProjectConstants.MESSAGE_201));
    }
    @GetMapping("/fetchAllClients")
    public ResponseEntity<List> fetchAllClients() {

        return ResponseEntity.status(HttpStatus.OK).body(clientService.fetchAllClients());
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody Client client) {
        boolean isUpdated = clientService.updateClient(client);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(ProjectConstants.STATUS_200, ProjectConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(ProjectConstants.STATUS_417, ProjectConstants.MESSAGE_417_UPDATE));
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                            Long id) {
        boolean isDeleted = clientService.deleteClient(id);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(ProjectConstants.STATUS_200, ProjectConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(ProjectConstants.STATUS_417, ProjectConstants.MESSAGE_417_DELETE));
        }
    }
}
