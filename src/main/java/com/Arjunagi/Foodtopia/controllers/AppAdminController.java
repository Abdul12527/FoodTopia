package com.Arjunagi.Foodtopia.controllers;

import com.Arjunagi.Foodtopia.models.Dto.AuthInpDto;
import com.Arjunagi.Foodtopia.models.Dto.LoginDto;
import com.Arjunagi.Foodtopia.models.Dto.RegisterDto;
import com.Arjunagi.Foodtopia.models.user.AppAdmin;
import com.Arjunagi.Foodtopia.models.user.Customer;
import com.Arjunagi.Foodtopia.models.user.RestaurantAdmin;
import com.Arjunagi.Foodtopia.services.AppAdminServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppAdminController {
    @Autowired
    private AppAdminServices appAdminServices;
    @PostMapping("app/admin")
    public String addAppAdmin(@RequestBody @Valid RegisterDto customer){
        return appAdminServices.addAppAdmin(customer);
    }
    @PostMapping("app/admin/login")
    public AuthInpDto login(@RequestBody @Valid LoginDto loginDto){
        return appAdminServices.login(loginDto);
    }
    @GetMapping("app/admin/{authTokenValue}")
    public AppAdmin getAppAdmin(@PathVariable String authTokenValue, @RequestParam String email){
        return appAdminServices.getAppAdmin(authTokenValue,email);
    }
    @GetMapping("app/admin/{authTokenValue}/customers")
    public List<Customer> getAllCustomer(@PathVariable String authTokenValue, @RequestParam String email){
        return appAdminServices.getAllCustomer(authTokenValue,email);
    }
    @GetMapping("app/admin/{authTokenValue}/restaurant/admins")
    public List<RestaurantAdmin> getAllRestaurantAdmin(@PathVariable String authTokenValue, @RequestParam String email){
        return appAdminServices.getAllRestaurantAdmin(authTokenValue,email);
    }
    @DeleteMapping("app/admin/logout/{authTokenValue}")
    public String logout(@PathVariable String authTokenValue,@RequestParam String email){
        return appAdminServices.logout(authTokenValue,email);
    }
}
