package com.Arjunagi.Foodtopia.services;

import com.Arjunagi.Foodtopia.models.AuthTokens.AppAdminAuthToken;
import com.Arjunagi.Foodtopia.models.Dto.AuthInpDto;
import com.Arjunagi.Foodtopia.models.Dto.LoginDto;
import com.Arjunagi.Foodtopia.models.Dto.RegisterDto;
import com.Arjunagi.Foodtopia.models.user.AppAdmin;
import com.Arjunagi.Foodtopia.models.user.Customer;
import com.Arjunagi.Foodtopia.models.user.RestaurantAdmin;
import com.Arjunagi.Foodtopia.repository.IAppAdminRepo;
import com.Arjunagi.Foodtopia.repository.ICustomerRepo;
import com.Arjunagi.Foodtopia.repository.IRestaurantAdminRepo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppAdminServices {
    @Autowired
    private IAppAdminRepo appAdminRepo;
    @Autowired
    private AppAdminAuthTokenServices appAdminAuthTokenServices;
    @Autowired
    private ICustomerRepo customerRepo;
    @Autowired
    private IRestaurantAdminRepo restaurantAdminRepo;
    @SneakyThrows
    public String addAppAdmin(RegisterDto customer) {
        AppAdmin appAdmin=new AppAdmin(null,customer.getName(),customer.getEmail(),customer.getPhoneNumber(),PasswordEncryptor.encrypt(customer.getPassword()), LocalDateTime.now());
        appAdminRepo.save(appAdmin);
        return "added sucessfully";
    }
    @SneakyThrows
    public AuthInpDto login(LoginDto loginDto) {
        AppAdmin appAdmin= appAdminRepo.findByEmail(loginDto.getEmail());
        if(appAdmin==null||!appAdmin.getPassword().equals(PasswordEncryptor.encrypt(loginDto.getPassword())))return null;
        return new AuthInpDto(appAdmin.getEmail(), appAdminAuthTokenServices.generateNewToken(appAdmin));
    }

    public String logout(String authTokenValue, String email) {
        AppAdminAuthToken authToken= appAdminAuthTokenServices.findByValue(authTokenValue);
        if(authToken==null||!authToken.getAppAdmin().getEmail().equals(email))return "un-autherized access";
        appAdminAuthTokenServices.deleteByTokenValue(authToken);
        return "logged out sucessfully";
    }

    public AppAdmin getAppAdmin(String authTokenValue, String email) {
        AppAdminAuthToken appAdminAuthToken= appAdminAuthTokenServices.findByValue(authTokenValue);
        if(appAdminAuthToken==null||!appAdminAuthToken.getAppAdmin().getEmail().equals(email))return null;
        return appAdminAuthToken.getAppAdmin();
    }

    public List<Customer> getAllCustomer(String authTokenValue, String email) {
        AppAdminAuthToken appAdminAuthToken= appAdminAuthTokenServices.findByValue(authTokenValue);
        if(appAdminAuthToken==null||!appAdminAuthToken.getAppAdmin().getEmail().equals(email))return null;
        return customerRepo.findAll();
    }
    public List<RestaurantAdmin> getAllRestaurantAdmin(String authTokenValue, String email){
        AppAdminAuthToken appAdminAuthToken= appAdminAuthTokenServices.findByValue(authTokenValue);
        if(appAdminAuthToken==null||!appAdminAuthToken.getAppAdmin().getEmail().equals(email))return null;
        return restaurantAdminRepo.findAll();
    }
}
