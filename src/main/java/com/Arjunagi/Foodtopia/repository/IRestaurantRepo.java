package com.Arjunagi.Foodtopia.repository;

import com.Arjunagi.Foodtopia.models.Restaurant;
import com.Arjunagi.Foodtopia.models.user.RestaurantAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantRepo extends JpaRepository<Restaurant,Integer> {

    Restaurant findByRestaurantAdmin(RestaurantAdmin restaurantAdmin);
}
