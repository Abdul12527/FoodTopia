package com.Arjunagi.Foodtopia.models.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressInpDTO {
    @NotBlank
    private String street;
    @NotBlank
    private String City;
    @NotBlank
    private String state;
    @NotBlank
    private String country;
    @NotBlank
    private String address;
    private Boolean isPrimary;
}
