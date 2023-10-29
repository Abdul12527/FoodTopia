package com.Arjunagi.Foodtopia.services;

import com.Arjunagi.Foodtopia.models.AuthTokens.RestaurantAdminAuthToken;
import com.Arjunagi.Foodtopia.models.Restaurant;
import com.Arjunagi.Foodtopia.models.address.RestaurantAddress;
import com.Arjunagi.Foodtopia.models.user.RestaurantAdmin;
import com.Arjunagi.Foodtopia.repository.IRestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServices {
    @Autowired
    private IRestaurantRepo restaurantRepo;
    @Autowired
    private RestaurantAdminAuthTokenServices restaurantAdminAuthTokenServices;
    @Autowired
    private RestaurantAddressServices restaurantAddressServices;

    public String addRestaurant(Restaurant restaurant, String authTokenValue, String email) {
        RestaurantAdminAuthToken restaurantAdminAuthToken=restaurantAdminAuthTokenServices.findByValue(authTokenValue);
        if(restaurantAdminAuthToken==null||!restaurantAdminAuthToken.getRestaurantAdmin().getEmail().equals(email)) return "wrong credentials";
        restaurant.setRestaurantAdmin(restaurantAdminAuthToken.getRestaurantAdmin());
        RestaurantAddress restaurantAddress=restaurantAddressServices.getAddressById(authTokenValue,email,restaurant.getRestaurantAddress().getId());
        if(restaurantAddress==null)return "wrong address";
        restaurant.setRestaurantAddress(restaurantAddress);
        restaurantRepo.save(restaurant);
        return "restaurant Added sucessfully";
    }

    public List<Restaurant> getAllRestaurant() {
        return restaurantRepo.findAll();
    }

    public Restaurant getRestaurantById(Integer id) {
        return restaurantRepo.findById(id).orElseThrow();
    }

    public String deleteRestaurant(String authTokenValue, String email, Integer restaurantId) {
        RestaurantAdminAuthToken restaurantAdminAuthToken=restaurantAdminAuthTokenServices.findByValue(authTokenValue);
        if(restaurantAdminAuthToken==null||!restaurantAdminAuthToken.getRestaurantAdmin().getEmail().equals(email)) return "wrong credentials";
        Restaurant restaurant=getRestaurantById(restaurantId);
        if(!restaurant.getRestaurantAdmin().equals(restaurantAdminAuthToken.getRestaurantAdmin()))return "un-autherized access";
        restaurantRepo.delete(restaurant);
        return "deleted sucessfully";
    }

    public Restaurant findByRestaurantAdmin(RestaurantAdmin restaurantAdmin) {
        return restaurantRepo.findByRestaurantAdmin(restaurantAdmin);
    }
}
