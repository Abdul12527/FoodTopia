package com.Arjunagi.Foodtopia.controllers;

import com.Arjunagi.Foodtopia.models.Dto.AuthInpDto;
import com.Arjunagi.Foodtopia.models.Dto.LoginDto;
import com.Arjunagi.Foodtopia.models.Dto.RegisterDto;
import com.Arjunagi.Foodtopia.models.user.Customer;
import com.Arjunagi.Foodtopia.services.CustomerServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    @Autowired
    CustomerServices customerServices;
    @PostMapping("customer")
    public String addCustomer(@RequestBody @Valid RegisterDto customer){
        return customerServices.addCustomer(customer);
    }
    @PostMapping("customer/login")
    public AuthInpDto login(@RequestBody @Valid LoginDto loginDto){
        return customerServices.login(loginDto);
    }
    @GetMapping("customer/{authTokenValue}")
    public Customer getCustomer(@PathVariable String authTokenValue,@RequestParam String email){
        return customerServices.getCustomer(authTokenValue,email);
    }
    @DeleteMapping("customer/logout/{authTokenValue}")
    public String logout(@PathVariable String authTokenValue,@RequestParam String email){
        return customerServices.logout(authTokenValue,email);
    }

}
