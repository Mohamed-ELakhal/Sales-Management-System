package com.example.sales.management.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "client")
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Audited
    @Column(name="name")
    private String name;
    @Audited
    @Column(name="lastname")
    private String lastName;
    @Audited
    @Column(name="address")
    private String address;
    @Audited
    @Column(name="mobile")
    private String mobile;
    @Audited
    @Column(name="email")
    private String email;

    @OneToMany(mappedBy = "client",orphanRemoval = true)
    private List<Sale> sales = new ArrayList<Sale>();

    public void addSale(Sale sale){
        if(sales==null)
            sales=new ArrayList<>();
        sales.add(sale);
        sale.setClient(this);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
