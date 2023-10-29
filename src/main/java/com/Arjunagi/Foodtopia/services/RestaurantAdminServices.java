package com.Arjunagi.Foodtopia.services;

import com.Arjunagi.Foodtopia.models.AuthTokens.CustomerAuthToken;
import com.Arjunagi.Foodtopia.models.AuthTokens.RestaurantAdminAuthToken;
import com.Arjunagi.Foodtopia.models.Dto.AuthInpDto;
import com.Arjunagi.Foodtopia.models.Dto.LoginDto;
import com.Arjunagi.Foodtopia.models.Dto.RegisterDto;
import com.Arjunagi.Foodtopia.models.user.Customer;
import com.Arjunagi.Foodtopia.models.user.RestaurantAdmin;
import com.Arjunagi.Foodtopia.repository.IRestaurantAdminRepo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RestaurantAdminServices {
    @Autowired
    private IRestaurantAdminRepo restaurantAdminRepo;
    @Autowired
    private RestaurantAdminAuthTokenServices restaurantAdminAuthTokenServices;
    @SneakyThrows
    public String addCustomer(RegisterDto customer) {
        RestaurantAdmin restaurantAdmin=new RestaurantAdmin(null,customer.getName(),customer.getEmail(),customer.getPhoneNumber(),PasswordEncryptor.encrypt(customer.getPassword()), LocalDateTime.now());
        restaurantAdminRepo.save(restaurantAdmin);
        return "added sucessfully";
    }
    @SneakyThrows
    public AuthInpDto login(LoginDto loginDto) {
        RestaurantAdmin restaurantAdmin = restaurantAdminRepo.findByEmail(loginDto.getEmail());
        if(restaurantAdmin ==null||!restaurantAdmin.getPassword().equals(PasswordEncryptor.encrypt(loginDto.getPassword())))return null;
        return new AuthInpDto(restaurantAdmin.getEmail(), restaurantAdminAuthTokenServices.generateNewToken(restaurantAdmin));
    }

    public String logout(String authTokenValue, String email) {
        RestaurantAdminAuthToken authToken= restaurantAdminAuthTokenServices.findByValue(authTokenValue);
        if(authToken==null||!authToken.getRestaurantAdmin().getEmail().equals(email))return "un-autherized access";
        restaurantAdminAuthTokenServices.deleteByTokenValue(authToken);
        return "logged out sucessfully";
    }

    public RestaurantAdmin getCustomer(String authTokenValue, String email) {
        RestaurantAdminAuthToken authToken= restaurantAdminAuthTokenServices.findByValue(authTokenValue);
        if(authToken==null||!authToken.getRestaurantAdmin().getEmail().equals(email))return null;
        return authToken.getRestaurantAdmin();
    }
}
