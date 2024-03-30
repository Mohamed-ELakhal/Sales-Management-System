package com.example.sales.management.system.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "account_id")
    private int id;

    private String email;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pwd;

    @CreationTimestamp
    @Column(name = "created_date",updatable = false)
    private LocalDateTime createdDate;

    @JsonIgnore
    @OneToMany(mappedBy="account",fetch=FetchType.EAGER)
    private Set<Authority> authorities;
}
