package com.Arjunagi.Foodtopia.models.AuthTokens;

import com.Arjunagi.Foodtopia.models.user.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CustomerAuthToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String value;
    private LocalDateTime creationTime;
    @OneToOne
    @JoinColumn(name = "fkCustomerId")
    private Customer customer;
    public CustomerAuthToken(Customer customer){
        this.customer=customer;
        creationTime=LocalDateTime.now();
        value= UUID.randomUUID().toString();
    }
}
