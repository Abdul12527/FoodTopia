package com.Arjunagi.Foodtopia.controllers;

import com.Arjunagi.Foodtopia.models.Dto.AuthInpDto;
import com.Arjunagi.Foodtopia.models.Dto.LoginDto;
import com.Arjunagi.Foodtopia.models.Dto.RegisterDto;
import com.Arjunagi.Foodtopia.models.user.Customer;
import com.Arjunagi.Foodtopia.models.user.RestaurantAdmin;
import com.Arjunagi.Foodtopia.services.RestaurantAdminServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestaurantAdminController {
    @Autowired
    RestaurantAdminServices restaurantAdminServices;
    @PostMapping("restaurant/admin")
    public String addCustomer(@RequestBody @Valid RegisterDto customer){
        return restaurantAdminServices.addCustomer(customer);
    }
    @PostMapping("restaurant/admin/login")
    public AuthInpDto login(@RequestBody @Valid LoginDto loginDto){
        return restaurantAdminServices.login(loginDto);
    }
    @GetMapping("restaurant/admin/{authTokenValue}")
    public RestaurantAdmin getCustomer(@PathVariable String authTokenValue, @RequestParam String email){
        return restaurantAdminServices.getCustomer(authTokenValue,email);
    }
    @DeleteMapping("restaurant/admin/logout/{authTokenValue}")
    public String logout(@PathVariable String authTokenValue,@RequestParam String email){
        return restaurantAdminServices.logout(authTokenValue,email);
    }
}
