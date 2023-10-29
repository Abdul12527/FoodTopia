package com.Arjunagi.Foodtopia.repository;

import com.Arjunagi.Foodtopia.models.user.AppAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppAdminRepo extends JpaRepository<AppAdmin,Integer> {
    AppAdmin findByEmail(String email);
}
