package com.Arjunagi.Foodtopia.models.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInputDto {
    private Integer customerAddressId;
    private List<Integer> foodItemsIds;
}
