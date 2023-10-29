package com.Arjunagi.Foodtopia.controllers;

import com.Arjunagi.Foodtopia.models.FoodItem;
import com.Arjunagi.Foodtopia.services.FoodItemServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodItemController {
    @Autowired
    private FoodItemServices foodItemServices;

    @PostMapping("foodItem/restaurant/admin/{authTokenValue}")
    public String addFoodItem(@RequestBody @Valid FoodItem foodItem, @PathVariable String authTokenValue, @RequestParam String email){
        return foodItemServices.addFoodItem(foodItem,authTokenValue,email);
    }
    @GetMapping("foodItems")
    public List<FoodItem> getAllFoodItems(){
        return foodItemServices.getAllFoodItems();
    }
    @GetMapping("foodItem/{foodItemId}")
    public FoodItem getFoodItemById(@PathVariable Integer foodItemId){
        return foodItemServices.getFoodItemById(foodItemId);
    }
    @GetMapping("foodItems/restaurant")
    public List<FoodItem> getFoodItemsByRestaurant(@RequestParam Integer restaurantId){
        return foodItemServices.getFoodItemsByRestaurant(restaurantId);
    }
    @PutMapping("foodItem/restaurant/admin/{authTokenValue}")
    public String updateFoodItem(@RequestBody @Valid FoodItem foodItem, @PathVariable String authTokenValue, @RequestParam String email){
        return foodItemServices.updateFoodItem(foodItem,authTokenValue,email);
    }
    @DeleteMapping("foodItem/{foodItemId}/restaurant/admin/{authTokenValue}")
    public String deleteFoodItem(@PathVariable Integer foodItemId , @PathVariable String authTokenValue, @RequestParam String email){
        return foodItemServices.deleteFoodItem(foodItemId,authTokenValue,email);
    }

}
