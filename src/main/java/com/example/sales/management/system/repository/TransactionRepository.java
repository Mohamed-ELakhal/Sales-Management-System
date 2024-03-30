package com.example.sales.management.system.repository;

import com.example.sales.management.system.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    @Query("SELECT t.product.id, t.product.name, SUM(t.quantity) AS totalQuantity " +
            "FROM Transaction t " +
            "WHERE t.createdDate BETWEEN :startDate AND :endDate " +
            "GROUP BY t.product.id, t.product.name " +
            "ORDER BY totalQuantity DESC")
    List<Object[]> findTop3SellingProductsInDateRange(@Param("startDate") LocalDateTime startDate,
                                                      @Param("endDate") LocalDateTime endDate);
}
