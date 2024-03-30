package com.example.sales.management.system.service;

import com.example.sales.management.system.dto.ClientDto;
import com.example.sales.management.system.entity.Client;
import com.example.sales.management.system.entity.Product;

import java.util.List;

public interface ClientService {
    void createClient(ClientDto clientDto);
    List<Client> fetchAllClients();
    boolean updateClient(Client client);
    long countClients();
    List<Object[]> findTopSpendingClientsWithTotalSpent();

    boolean deleteClient(Long id);
}
