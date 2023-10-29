package com.Arjunagi.Foodtopia.services;

import com.Arjunagi.Foodtopia.models.AuthTokens.CustomerAuthToken;
import com.Arjunagi.Foodtopia.models.Dto.AuthInpDto;
import com.Arjunagi.Foodtopia.models.Dto.LoginDto;
import com.Arjunagi.Foodtopia.models.Dto.RegisterDto;
import com.Arjunagi.Foodtopia.models.user.Customer;
import com.Arjunagi.Foodtopia.repository.ICustomerRepo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerServices {
    @Autowired
    private ICustomerRepo customerRepo;
    @Autowired
    private CustomerAuthTokenServices customerAuthTokenServices;
    @SneakyThrows
    public String addCustomer(RegisterDto customer) {
        Customer customer1=new Customer(null,customer.getName(),customer.getEmail(),customer.getPhoneNumber(),PasswordEncryptor.encrypt(customer.getPassword()), LocalDateTime.now());
        customerRepo.save(customer1);
        return "added sucessfully";
    }
    @SneakyThrows
    public AuthInpDto login(LoginDto loginDto) {
        Customer customer=customerRepo.findByEmail(loginDto.getEmail());
        if(customer==null||!customer.getPassword().equals(PasswordEncryptor.encrypt(loginDto.getPassword())))return null;
        CustomerAuthToken customerAuthToken=customerAuthTokenServices.findByCustomer(customer);
        if(customerAuthToken!=null)return new AuthInpDto(customer.getEmail(), customerAuthToken.getValue());
        return new AuthInpDto(customer.getEmail(), customerAuthTokenServices.generateNewToken(customer));
    }

    public String logout(String authTokenValue, String email) {
        CustomerAuthToken authToken= customerAuthTokenServices.findByValue(authTokenValue);
        if(authToken==null||!authToken.getCustomer().getEmail().equals(email))return "un-autherized access";
        customerAuthTokenServices.deleteByTokenValue(authToken);
        return "logged out sucessfully";
    }

    public Customer getCustomer(String authTokenValue, String email) {
        CustomerAuthToken authToken= customerAuthTokenServices.findByValue(authTokenValue);
        if(authToken==null||!authToken.getCustomer().getEmail().equals(email))return null;
        return authToken.getCustomer();
    }
}
