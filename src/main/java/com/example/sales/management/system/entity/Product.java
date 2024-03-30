package com.example.sales.management.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "product")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")

    private String name;

    @Column(name="quantity")
    private double quantity;
    @Audited
    @Column(name="price")
    private double price;

    @Column(name = "category")
    private String category;
    @CreationTimestamp
    @Column(name = "created_date",updatable = false)
    private LocalDateTime createdDate;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "product",orphanRemoval = true)
    private List<Transaction> transactions;

    public void addTransaction(Transaction transaction){
        if(transactions==null)
            transactions=new ArrayList<>();
        transactions.add(transaction);
        transaction.setProduct(this);
    }
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", createdDate=" + createdDate +
                ", description='" + description + '\'' +
                '}';
    }

}
