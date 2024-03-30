package com.example.sales.management.system.dto;

import lombok.Data;

import java.util.List;
@Data
public class SaleReportDto {
    private List<Object[]> topProducts;
    private long totalNumberofSales;
    private double totalRevenue;
    private List<Object[]> topSeller;

    @Override
    public String toString() {
        return "SaleReportDto{" +
                "topProducts=" + topProducts +
                ", totalNumberofSales=" + totalNumberofSales +
                ", totalRevenue=" + totalRevenue +
                ", topSeller=" + topSeller +
                '}';
    }

}
