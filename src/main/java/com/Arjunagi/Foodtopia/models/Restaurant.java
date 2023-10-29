package com.Arjunagi.Foodtopia.models;

import com.Arjunagi.Foodtopia.models.address.RestaurantAddress;
import com.Arjunagi.Foodtopia.models.user.RestaurantAdmin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String Speciality;
    private CuisineType cuisineType;
    private String restaurantImageUrl;
    @NotNull
    private LocalTime openingTime;
    @NotNull
    private LocalTime closingTime;
    private Boolean isOnToday;
    @OneToOne
    @JoinColumn(name = "fkRestaurantAddressId")
    private RestaurantAddress restaurantAddress;
    @OneToOne
    @JoinColumn(name = "fkRestaurantAdminId")
    @JsonIgnore
    private RestaurantAdmin restaurantAdmin;
}
