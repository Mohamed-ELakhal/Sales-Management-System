package com.example.sales.management.system.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClientReportDto {
    long totalNumberOfClients;
    List<Object[]> topSpendingClients;

    @Override
    public String toString() {
        return "ClientReportDto{" +
                "totalNumberOfClients=" + totalNumberOfClients +
                ", topSpendingClients=" + topSpendingClients +
                '}';
    }
}
