package com.Arjunagi.Foodtopia.controllers;

import com.Arjunagi.Foodtopia.models.Dto.AddressInpDTO;
import com.Arjunagi.Foodtopia.models.address.CustomerAddress;
import com.Arjunagi.Foodtopia.models.address.RestaurantAddress;
import com.Arjunagi.Foodtopia.services.RestaurantAddressServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantAddressController {
    @Autowired
    private RestaurantAddressServices restaurantAddressServices;

    @PostMapping("address/restaurant/{authTokenValue}")
    public String addRestaurantAddress(@RequestBody @Valid AddressInpDTO addressInpDTO, String authTokenValue, @RequestParam String email){
        return restaurantAddressServices.addRestaurantAddress(addressInpDTO,authTokenValue,email);
    }
    @GetMapping("addresses/restaurant/{authTokenValue}")
    public List<RestaurantAddress> getAllAddressForRestaurantAdmin(@PathVariable String authTokenValue, @RequestParam String email){
        return restaurantAddressServices.getAllAddressForRestaurantAdmin(authTokenValue,email);
    }
    @GetMapping("address/restaurant/{authTokenValue}")
    public RestaurantAddress getAddressById(@PathVariable String authTokenValue,@RequestParam String email,@RequestParam Integer addressId){
        return restaurantAddressServices.getAddressById(authTokenValue,email,addressId);
    }
    @PutMapping("address/restaurant/{authTokenValue}")
    private String updateAddress(@RequestBody @Valid AddressInpDTO addressInpDTO,@PathVariable String authTokenValue,@RequestParam String email,@RequestParam Integer addressId){
        return restaurantAddressServices.updateAddress(addressInpDTO,authTokenValue,email,addressId);
    }
    @DeleteMapping("address/restaurant/{authTokenValue}")
    public String deleteAddress(@PathVariable String authTokenValue,@RequestParam String email,@RequestParam Integer addressId){
        return restaurantAddressServices.deleteAddress(authTokenValue,email,addressId);
    }
}
