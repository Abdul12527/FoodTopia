package com.Arjunagi.Foodtopia.controllers;

import com.Arjunagi.Foodtopia.models.Dto.AddressInpDTO;
import com.Arjunagi.Foodtopia.models.address.CustomerAddress;
import com.Arjunagi.Foodtopia.services.CustomerAddressServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerAddressController {
    @Autowired
    private CustomerAddressServices customerAddressServices;

    @PostMapping("address/customer/{authTokenValue}")
    public String addCustomerAddress(@RequestBody @Valid AddressInpDTO addressInpDTO, String authTokenValue, @RequestParam String email){
        return customerAddressServices.addCustomerAddress(addressInpDTO,authTokenValue,email);
    }
    @GetMapping("addresses/customer/{authTokenValue}")
    public List<CustomerAddress> getAllAddressForCustomer(@PathVariable String authTokenValue,@RequestParam String email){
        return customerAddressServices.getAllAddressForCustomer(authTokenValue,email);
    }
    @GetMapping("address/customer/{authTokenValue}")
    public CustomerAddress getAddressById(@PathVariable String authTokenValue,@RequestParam String email,@RequestParam Integer addressId){
        return customerAddressServices.getAddressById(authTokenValue,email,addressId);
    }
    @PutMapping("address/customer/{authTokenValue}")
    private String updateAddress(@RequestBody @Valid AddressInpDTO addressInpDTO,@PathVariable String authTokenValue,@RequestParam String email,@RequestParam Integer addressId){
        return customerAddressServices.updateAddress(addressInpDTO,authTokenValue,email,addressId);
    }
    @DeleteMapping("address/customer/{authTokenValue}")
    public String deleteAddress(@PathVariable String authTokenValue,@RequestParam String email,@RequestParam Integer addressId){
        return customerAddressServices.deleteAddress(authTokenValue,email,addressId);
    }


}
