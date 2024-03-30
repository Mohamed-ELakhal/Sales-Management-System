package com.example.sales.management.system.service;

import com.example.sales.management.system.dto.ClientDto;
import com.example.sales.management.system.entity.Client;
import com.example.sales.management.system.entity.Product;
import com.example.sales.management.system.exception.AlreadyExistsException;
import com.example.sales.management.system.exception.ResourceNotFoundException;
import com.example.sales.management.system.mapper.ClientMapper;
import com.example.sales.management.system.repository.ClientRepository;
import com.example.sales.management.system.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class ClientServiceJPA implements ClientService{
    private ClientRepository clientRepository;
    private ProductRepository productRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Override
    public void createClient(ClientDto clientDto) {
        Client client= ClientMapper.mapToClient(clientDto,new Client());
        Optional<Client> optionalClient = clientRepository.findByEmail(clientDto.getEmail());
        if(optionalClient.isPresent())
            throw new AlreadyExistsException("The Client already exists with the specified email");
        clientRepository.save(client);
    }

    @Override
    public List<Client> fetchAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public boolean updateClient(Client client) {
        boolean isUpdated = false;
        Optional<Client> existingClientOptional = clientRepository.findById(client.getId());
        if (existingClientOptional.isPresent()) {
            Client existingClient = existingClientOptional.get();

            // Log client before update with current time
            logger.info("Client before update at {}: {}", getCurrentDateTime(), existingClient);

            // Update the client
            clientRepository.save(client);
            isUpdated = true;

            // Log client after update with current time
            logger.info("Client after update at {}: {}", getCurrentDateTime(), client);
        } else {
            throw new ResourceNotFoundException("Client", "ClientId", client.getId().toString());
        }

        return isUpdated;
    }

    @Override
    public long countClients() {
        return clientRepository.countClients();
    }

    @Override
    public List<Object[]> findTopSpendingClientsWithTotalSpent() {
        return clientRepository.findTopSpendingClientsWithTotalSpent();
    }

    @Override
    public boolean deleteClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Client", "ID", id.toString())
        );
        clientRepository.deleteById(client.getId());
        return true;
    }

    private String getCurrentDateTime() {
        return LocalDateTime.now().format(dateTimeFormatter);
    }
}
