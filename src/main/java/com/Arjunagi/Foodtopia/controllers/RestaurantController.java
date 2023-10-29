package com.Arjunagi.Foodtopia.controllers;

import com.Arjunagi.Foodtopia.models.Restaurant;
import com.Arjunagi.Foodtopia.services.RestaurantServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class RestaurantController {
    @Autowired
    private RestaurantServices restaurantServices;

    @PostMapping("restaurant/{authTokenValue}")//same can be used for updation just need add correct id
    public String addRestaurant(@RequestBody @Valid Restaurant restaurant, @PathVariable String authTokenValue, @RequestParam String email){
        return restaurantServices.addRestaurant(restaurant,authTokenValue,email);
    }
    @GetMapping("restaurants")
    public List<Restaurant> getAllRestaurant(){
        return restaurantServices.getAllRestaurant();
    }
    @GetMapping("restaurant/{id}")
    public Restaurant getRestaurantById(@PathVariable Integer id){
        return restaurantServices.getRestaurantById(id);
    }
    @DeleteMapping("restaurant/{authTokenValue}/{restaurantId}")
    public String deleteRestaurant(@PathVariable String authTokenValue, @RequestParam String email,@PathVariable Integer restaurantId){
        return restaurantServices.deleteRestaurant(authTokenValue,email,restaurantId);
    }
}
