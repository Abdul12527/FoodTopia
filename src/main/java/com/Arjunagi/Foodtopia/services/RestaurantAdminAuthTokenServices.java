package com.Arjunagi.Foodtopia.services;

import com.Arjunagi.Foodtopia.models.AuthTokens.RestaurantAdminAuthToken;
import com.Arjunagi.Foodtopia.models.user.RestaurantAdmin;
import com.Arjunagi.Foodtopia.repository.IRestaurantAdminAuthTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantAdminAuthTokenServices {
    @Autowired
    private IRestaurantAdminAuthTokenRepo restaurantAdminAuthTokenRepo;

    public String generateNewToken(RestaurantAdmin restaurantAdmin){
        RestaurantAdminAuthToken restaurantAdminAuthToken=new RestaurantAdminAuthToken(restaurantAdmin);
        restaurantAdminAuthTokenRepo.save(restaurantAdminAuthToken);
        return restaurantAdminAuthToken.getValue();
    }

    public RestaurantAdminAuthToken findByValue(String authTokenValue) {
        return restaurantAdminAuthTokenRepo.findByValue(authTokenValue);
    }

    public void deleteByTokenValue(RestaurantAdminAuthToken authToken) {
        restaurantAdminAuthTokenRepo.delete(authToken);
    }
}
