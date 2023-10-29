package com.Arjunagi.Foodtopia.services;

import com.Arjunagi.Foodtopia.models.AuthTokens.CustomerAuthToken;
import com.Arjunagi.Foodtopia.models.user.Customer;
import com.Arjunagi.Foodtopia.repository.ICustomerAuthTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerAuthTokenServices {
    @Autowired
    ICustomerAuthTokenRepo customerAuthTokenRepo;

    public String generateNewToken(Customer customer){
        CustomerAuthToken customerAuthToken=new CustomerAuthToken(customer);
        customerAuthTokenRepo.save(customerAuthToken);
        return customerAuthToken.getValue();
    }

    public CustomerAuthToken findByValue(String authTokenValue) {
        return customerAuthTokenRepo.findByValue(authTokenValue);
    }

    public void deleteByTokenValue(CustomerAuthToken authToken) {
        customerAuthTokenRepo.delete(authToken);
    }

    public CustomerAuthToken findByCustomer(Customer customer) {
        return customerAuthTokenRepo.findByCustomer(customer);
    }
}
