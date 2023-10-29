package com.Arjunagi.Foodtopia.models.address;

import com.Arjunagi.Foodtopia.models.user.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CustomerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String City;
    private String state;
    private String country;
    private Boolean isPrimary;
    @JsonIgnore
    private Boolean deleted;
    @ManyToOne
    @JoinColumn(name = "fkCustomerId")
    private Customer customer;
}
