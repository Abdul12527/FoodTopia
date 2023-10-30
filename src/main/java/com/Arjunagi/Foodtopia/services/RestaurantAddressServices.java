package com.Arjunagi.Foodtopia.services;

import com.Arjunagi.Foodtopia.models.AuthTokens.RestaurantAdminAuthToken;
import com.Arjunagi.Foodtopia.models.Dto.AddressInpDTO;
import com.Arjunagi.Foodtopia.models.address.RestaurantAddress;
import com.Arjunagi.Foodtopia.repository.IRestaurantAddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantAddressServices {
    @Autowired
    private IRestaurantAddressRepo restaurantAddressRepo;
    @Autowired
    private RestaurantAdminAuthTokenServices restaurantAdminAuthTokenServices;

    public String addRestaurantAddress(AddressInpDTO addressInpDTO, String authTokenValue, String email) {
        RestaurantAdminAuthToken restaurantAdminAuthToken= restaurantAdminAuthTokenServices.findByValue(authTokenValue);
        if(restaurantAdminAuthToken==null||!restaurantAdminAuthToken.getRestaurantAdmin().getEmail().equals(email))return "wrong credentials";
        RestaurantAddress restaurantAddress=new RestaurantAddress(null,addressInpDTO.getStreet(),addressInpDTO.getCity(),addressInpDTO.getState(),addressInpDTO.getCountry(),false,restaurantAdminAuthToken.getRestaurantAdmin());
        restaurantAddressRepo.save(restaurantAddress);
        return "address added sucessfully";
    }

    public List<RestaurantAddress> getAllAddressForRestaurantAdmin(String authTokenValue, String email) {
        RestaurantAdminAuthToken restaurantAdminAuthToken= restaurantAdminAuthTokenServices.findByValue(authTokenValue);
        if(restaurantAdminAuthToken==null||!restaurantAdminAuthToken.getRestaurantAdmin().getEmail().equals(email))return null;
        System.out.println("------------------"+restaurantAddressRepo.findAllByRestaurantAdminAndDeleted(restaurantAdminAuthToken.getRestaurantAdmin(),false));
        return restaurantAddressRepo.findAllByRestaurantAdminAndDeleted(restaurantAdminAuthToken.getRestaurantAdmin(),false);
    }

    public RestaurantAddress getAddressById(String authTokenValue, String email, Integer addressId) {
        RestaurantAdminAuthToken restaurantAdminAuthToken= restaurantAdminAuthTokenServices.findByValue(authTokenValue);
        if(restaurantAdminAuthToken==null||!restaurantAdminAuthToken.getRestaurantAdmin().getEmail().equals(email))return null;
        RestaurantAddress restaurantAddress= restaurantAddressRepo.findById(addressId).orElseThrow();
        if(!restaurantAddress.getRestaurantAdmin().equals(restaurantAdminAuthToken.getRestaurantAdmin())||restaurantAddress.getDeleted())return null;
        return restaurantAddress;
    }

    public String updateAddress(AddressInpDTO addressInpDTO, String authTokenValue, String email,Integer addressId) {
        RestaurantAdminAuthToken restaurantAdminAuthToken= restaurantAdminAuthTokenServices.findByValue(authTokenValue);
        if(restaurantAdminAuthToken==null||!restaurantAdminAuthToken.getRestaurantAdmin().getEmail().equals(email))return "wrong credentials";
        RestaurantAddress restaurantAddress= restaurantAddressRepo.findById(addressId).orElseThrow();
        if(!restaurantAddress.getRestaurantAdmin().equals(restaurantAdminAuthToken.getRestaurantAdmin()))return "un-autherized access";
        restaurantAddress.setDeleted(true);
        restaurantAddressRepo.save(restaurantAddress);
        RestaurantAddress restaurantAddress1=new RestaurantAddress(null,addressInpDTO.getStreet(),addressInpDTO.getCity(),addressInpDTO.getState(),addressInpDTO.getCountry(),false,restaurantAdminAuthToken.getRestaurantAdmin());
        restaurantAddressRepo.save(restaurantAddress1);
        return "updated sucessfully";
    }



    public String deleteAddress(String authTokenValue, String email, Integer addressId) {
        RestaurantAdminAuthToken restaurantAdminAuthToken= restaurantAdminAuthTokenServices.findByValue(authTokenValue);
        if(restaurantAdminAuthToken==null||!restaurantAdminAuthToken.getRestaurantAdmin().getEmail().equals(email))return "wrong credentials";
        RestaurantAddress restaurantAddress= restaurantAddressRepo.findById(addressId).orElseThrow();
        if(!restaurantAddress.getRestaurantAdmin().equals(restaurantAdminAuthToken.getRestaurantAdmin()))return "un-autherized access";
        restaurantAddress.setDeleted(true);
        restaurantAddressRepo.save(restaurantAddress);
        return "deleted sucessfully";
    }
}
