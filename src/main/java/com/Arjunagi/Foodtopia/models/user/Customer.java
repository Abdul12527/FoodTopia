package com.Arjunagi.Foodtopia.models.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String name;
    @Column(unique = true)
    @Email
    private String email;
    @Pattern(regexp = "^[789]\\d{9}$")
    private String phoneNumber;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private LocalDateTime creationTime;
}
