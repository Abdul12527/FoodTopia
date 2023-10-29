package com.Arjunagi.Foodtopia.repository;

import com.Arjunagi.Foodtopia.models.user.RestaurantAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantAdminRepo extends JpaRepository<RestaurantAdmin,Integer> {
    RestaurantAdmin findByEmail(String email);
}
