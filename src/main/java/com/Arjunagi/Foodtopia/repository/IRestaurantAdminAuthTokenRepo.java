package com.Arjunagi.Foodtopia.repository;

import com.Arjunagi.Foodtopia.models.AuthTokens.CustomerAuthToken;
import com.Arjunagi.Foodtopia.models.AuthTokens.RestaurantAdminAuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantAdminAuthTokenRepo extends JpaRepository<RestaurantAdminAuthToken,Integer> {
    RestaurantAdminAuthToken findByValue(String authTokenValue);
}
