package com.Arjunagi.Foodtopia.repository;

import com.Arjunagi.Foodtopia.models.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepo extends JpaRepository<Customer,Integer> {
    Customer findByEmail(String email);
}
