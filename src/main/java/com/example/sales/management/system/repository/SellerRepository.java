package com.example.sales.management.system.repository;

import com.example.sales.management.system.entity.Product;
import com.example.sales.management.system.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller,Long> {
    Optional<Seller> findByEmail(String email);
}
