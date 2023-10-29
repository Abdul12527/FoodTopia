package com.Arjunagi.Foodtopia.repository;

import com.Arjunagi.Foodtopia.models.AuthTokens.AppAdminAuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppAdminAuthTokenRepo extends JpaRepository<AppAdminAuthToken,Integer> {
    AppAdminAuthToken findByValue(String authTokenValue);
}
