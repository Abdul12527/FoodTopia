package com.Arjunagi.Foodtopia.models.AuthTokens;

import com.Arjunagi.Foodtopia.models.user.AppAdmin;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AppAdminAuthToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String value;
    private LocalDateTime creationTime;
    @OneToOne
    @JoinColumn(name = "fkCustomerId")
    private AppAdmin appAdmin;
    public AppAdminAuthToken(AppAdmin appAdmin){
        this.appAdmin=appAdmin;
        creationTime=LocalDateTime.now();
        value= UUID.randomUUID().toString();
    }
}
