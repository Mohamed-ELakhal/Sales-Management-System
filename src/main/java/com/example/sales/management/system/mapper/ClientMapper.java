package com.example.sales.management.system.mapper;

import com.example.sales.management.system.dto.ClientDto;
import com.example.sales.management.system.entity.Client;


public class ClientMapper {
    public static ClientDto mapToClientDto(Client client, ClientDto clientDto){
        clientDto.setName (client.getName());
        clientDto.setEmail(client.getEmail());
        clientDto.setMobile(client.getMobile());
        clientDto.setLastName(client.getLastName());
        clientDto.setAddress(client.getAddress());
        return clientDto;
    }
    public static Client mapToClient(ClientDto clientDto,Client client){
        client.setName (clientDto.getName());
        client.setEmail(clientDto.getEmail());
        client.setMobile(clientDto.getMobile());
        client.setLastName(clientDto.getLastName());
        client.setAddress(clientDto.getAddress());
        return client;
    }
}
