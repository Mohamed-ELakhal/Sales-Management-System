package com.example.sales.management.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "seller")
@Entity
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="lastname")
    private String lastName;

    @Column(name="address")
    private String address;

    @Column(name="mobile")
    private String mobile;
    @Column(name="email")
    private String email;

    @OneToMany(mappedBy = "seller",orphanRemoval = true)
    private List<Sale> sales = new ArrayList<Sale>();

    public void addSale(Sale sale){
        if(sales==null)
            sales=new ArrayList<>();
        sales.add(sale);
        sale.setSeller(this);
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
