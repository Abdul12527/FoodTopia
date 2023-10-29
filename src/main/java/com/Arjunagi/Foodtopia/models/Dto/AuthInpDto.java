package com.Arjunagi.Foodtopia.models.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthInpDto {
    private String email;
    private String AuthTokenValue;

}
