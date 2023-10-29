package com.Arjunagi.Foodtopia.repository;

import com.Arjunagi.Foodtopia.models.CustomerOrder;
import com.Arjunagi.Foodtopia.models.OrderStatus;
import com.Arjunagi.Foodtopia.models.Restaurant;
import com.Arjunagi.Foodtopia.models.user.Customer;
import com.Arjunagi.Foodtopia.models.user.RestaurantAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepo extends JpaRepository<CustomerOrder,Integer> {
    List<CustomerOrder> findAllByCustomerAddress_Customer(Customer customer);
    List<CustomerOrder> findAllByRestaurant_RestaurantAdmin(RestaurantAdmin restaurantAdmin);

    List<CustomerOrder>  findAllByCustomerAddress_CustomerAndRestaurantAndStatusNotAndStatusNot(Customer customer, Restaurant restaurant, OrderStatus delivered,OrderStatus cancelled);
}
