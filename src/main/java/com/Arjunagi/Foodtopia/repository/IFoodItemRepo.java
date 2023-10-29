package com.Arjunagi.Foodtopia.repository;

import com.Arjunagi.Foodtopia.models.FoodItem;
import com.Arjunagi.Foodtopia.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFoodItemRepo extends JpaRepository<FoodItem,Integer> {
    List<FoodItem> findByRestaurant(Restaurant restaurantById);
}
