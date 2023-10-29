package com.Arjunagi.Foodtopia.repository;

import com.Arjunagi.Foodtopia.models.AuthTokens.CustomerAuthToken;
import com.Arjunagi.Foodtopia.models.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerAuthTokenRepo extends JpaRepository<CustomerAuthToken,Integer> {
    CustomerAuthToken findByValue(String authTokenValue);

    CustomerAuthToken findByCustomer(Customer customer);
}
