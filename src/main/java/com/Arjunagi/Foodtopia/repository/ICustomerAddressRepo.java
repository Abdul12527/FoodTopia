package com.Arjunagi.Foodtopia.repository;

import com.Arjunagi.Foodtopia.models.address.CustomerAddress;
import com.Arjunagi.Foodtopia.models.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICustomerAddressRepo extends JpaRepository<CustomerAddress,Integer> {
    List<CustomerAddress> findAllByCustomer(Customer customer);

    CustomerAddress findByCustomerAndIsPrimary(Customer customer, boolean b);


    List<CustomerAddress> findAllByCustomerAndDeleted(Customer customer, boolean b);
}
