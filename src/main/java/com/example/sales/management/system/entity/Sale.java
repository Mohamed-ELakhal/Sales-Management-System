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
@Table(name = "Sale")
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_seller")
    private Seller seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_client")
    private Client client;

    @Column(name="total")
    private double totalAmount;

    @OneToMany(mappedBy = "sale",orphanRemoval = true)
    private List<Transaction> transactions;
    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    public void addTransaction(Transaction transaction){
        if(transactions==null)
            transactions=new ArrayList<>();
        this.totalAmount+=transaction.getPrice();
        transactions.add(transaction);
        transaction.setSale(this);
    }
    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", totalAmount=" + totalAmount +
                ", createdDate=" + createdDate +
                '}';
    }
}
