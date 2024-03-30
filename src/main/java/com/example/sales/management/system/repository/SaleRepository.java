package com.example.sales.management.system.repository;

import com.example.sales.management.system.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {
    @Query("SELECT COUNT(s) FROM Sale s WHERE s.createdDate BETWEEN :startDate AND :endDate")
    long countSalesBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);



    @Query("SELECT se.email, SUM(sa.totalAmount) AS totalRevenue " +
            "FROM Sale sa " +
            "JOIN sa.seller se " +
            "WHERE sa.createdDate BETWEEN :startDate AND :endDate " +
            "GROUP BY se " +
            "ORDER BY totalRevenue DESC")
    List<Object[]> findTopSellersByRevenueBetweenDates(@Param("startDate") LocalDateTime startDate,
                                                       @Param("endDate") LocalDateTime endDate);

    @Query("SELECT SUM(s.totalAmount) " +
            "FROM Sale s " +
            "WHERE s.createdDate BETWEEN :startDate AND :endDate")
    Double getTotalRevenueBetweenDates(@Param("startDate") LocalDateTime startDate,
                                       @Param("endDate") LocalDateTime endDate);
}
