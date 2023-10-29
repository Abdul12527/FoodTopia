package com.Arjunagi.Foodtopia.models.address;

import com.Arjunagi.Foodtopia.models.user.RestaurantAdmin;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class RestaurantAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String City;
    private String state;
    private String country;
    private Boolean deleted;
    @ManyToOne
    private RestaurantAdmin restaurantAdmin;
}
