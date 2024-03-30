package com.example.sales.management.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "transaction")
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="quantity")
    private double quantity;
    @Column(name="price")
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_product")
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_sale")
    private Sale sale;
    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                ", createdDate=" + createdDate +
                '}';
    }
}
