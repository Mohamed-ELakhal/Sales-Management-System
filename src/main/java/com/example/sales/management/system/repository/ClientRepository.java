package com.example.sales.management.system.repository;

import com.example.sales.management.system.entity.Client;
import com.example.sales.management.system.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Optional<Client> findByEmail(String email);
    @Query("SELECT COUNT(c) FROM Client c")
    long countClients();
    @Query("SELECT c.email, SUM(s.totalAmount) AS totalSpent " +
            "FROM Client c JOIN c.sales s " +
            "GROUP BY c.id " +
            "ORDER BY totalSpent DESC")
    List<Object[]> findTopSpendingClientsWithTotalSpent();
}
