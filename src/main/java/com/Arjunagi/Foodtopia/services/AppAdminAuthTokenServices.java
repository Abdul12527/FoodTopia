package com.Arjunagi.Foodtopia.services;

import com.Arjunagi.Foodtopia.models.AuthTokens.AppAdminAuthToken;
import com.Arjunagi.Foodtopia.models.user.AppAdmin;
import com.Arjunagi.Foodtopia.repository.IAppAdminAuthTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppAdminAuthTokenServices {
    @Autowired
    private IAppAdminAuthTokenRepo appAdminAuthTokenRepo;

    public String generateNewToken(AppAdmin appAdmin){
        AppAdminAuthToken customerAuthToken=new AppAdminAuthToken(appAdmin);
        appAdminAuthTokenRepo.save(customerAuthToken);
        return customerAuthToken.getValue();
    }

    public AppAdminAuthToken findByValue(String authTokenValue) {
        return appAdminAuthTokenRepo.findByValue(authTokenValue);
    }

    public void deleteByTokenValue(AppAdminAuthToken appAdminAuthToken) {
        appAdminAuthTokenRepo.delete(appAdminAuthToken);
    }
}
