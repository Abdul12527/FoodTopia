package com.Arjunagi.Foodtopia.services;

import com.Arjunagi.Foodtopia.models.AuthTokens.RestaurantAdminAuthToken;
import com.Arjunagi.Foodtopia.models.FoodItem;
import com.Arjunagi.Foodtopia.models.Restaurant;
import com.Arjunagi.Foodtopia.repository.IFoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemServices {
    @Autowired
    private IFoodItemRepo foodItemRepo;
    @Autowired
    private RestaurantAdminAuthTokenServices restaurantAdminAuthTokenServices;
    @Autowired
    private RestaurantServices restaurantServices;

    public String addFoodItem(FoodItem foodItem, String authTokenValue, String email) {
        RestaurantAdminAuthToken restaurantAdminAuthToken=restaurantAdminAuthTokenServices.findByValue(authTokenValue);
        if(restaurantAdminAuthToken==null||!restaurantAdminAuthToken.getRestaurantAdmin().getEmail().equals(email))return "Wrong credentials";
        Restaurant restaurant=restaurantServices.findByRestaurantAdmin(restaurantAdminAuthToken.getRestaurantAdmin());
        foodItem.setRestaurant(restaurant);
        foodItemRepo.save(foodItem);
        return "food Item added sucessfully";
    }

    public List<FoodItem> getAllFoodItems() {
        return foodItemRepo.findAll();
    }

    public FoodItem getFoodItemById(Integer foodItemId) {
        return foodItemRepo.findById(foodItemId).orElseThrow();
    }

    public List<FoodItem> getFoodItemsByRestaurant(Integer restaurantId) {
        return foodItemRepo.findByRestaurant(restaurantServices.getRestaurantById(restaurantId));
    }

    public String updateFoodItem(FoodItem foodItem, String authTokenValue, String email) {
        RestaurantAdminAuthToken restaurantAdminAuthToken=restaurantAdminAuthTokenServices.findByValue(authTokenValue);
        if(restaurantAdminAuthToken==null||!restaurantAdminAuthToken.getRestaurantAdmin().getEmail().equals(email))return "Wrong credentials";
        if(foodItem.getId()==null)return "invalid food id";
        FoodItem currentFoodItem=foodItemRepo.findById(foodItem.getId()).orElseThrow();
        if(!currentFoodItem.getRestaurant().getRestaurantAdmin().equals(restaurantAdminAuthToken.getRestaurantAdmin()))return "un-autherized access";
        foodItem.setRestaurant(currentFoodItem.getRestaurant());
        foodItemRepo.save(foodItem);
        return "updated sucessfully";
    }

    public String deleteFoodItem(Integer foodItemId, String authTokenValue, String email) {
        RestaurantAdminAuthToken restaurantAdminAuthToken=restaurantAdminAuthTokenServices.findByValue(authTokenValue);
        if(restaurantAdminAuthToken==null||!restaurantAdminAuthToken.getRestaurantAdmin().getEmail().equals(email))return "Wrong credentials";
        FoodItem currentFoodItem=foodItemRepo.findById(foodItemId).orElseThrow();
        if(!currentFoodItem.getRestaurant().getRestaurantAdmin().equals(restaurantAdminAuthToken.getRestaurantAdmin()))return "un-autherized access";
        foodItemRepo.delete(currentFoodItem);
        return "deleted sucessfully";
    }
}
