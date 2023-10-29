package com.Arjunagi.Foodtopia.models.AuthTokens;

import com.Arjunagi.Foodtopia.models.user.RestaurantAdmin;
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
public class RestaurantAdminAuthToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String value;
    private LocalDateTime creationTime;
    @OneToOne
    @JoinColumn(name = "fkCustomerId")
    private RestaurantAdmin restaurantAdmin;
    public RestaurantAdminAuthToken(RestaurantAdmin restaurantAdmin){
        this.restaurantAdmin=restaurantAdmin;
        creationTime=LocalDateTime.now();
        value= UUID.randomUUID().toString();
    }
}
