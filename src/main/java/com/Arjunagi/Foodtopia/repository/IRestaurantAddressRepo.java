package com.Arjunagi.Foodtopia.repository;

import com.Arjunagi.Foodtopia.models.address.CustomerAddress;
import com.Arjunagi.Foodtopia.models.address.RestaurantAddress;
import com.Arjunagi.Foodtopia.models.user.Customer;
import com.Arjunagi.Foodtopia.models.user.RestaurantAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRestaurantAddressRepo extends JpaRepository<RestaurantAddress,Integer> {
    List<RestaurantAddress> findAllByRestaurantAdmin(RestaurantAdmin restaurantAdmin);

    List<RestaurantAddress> findAllByRestaurantAdminAndDeleted(RestaurantAdmin restaurantAdmin,Boolean deleted);
}
