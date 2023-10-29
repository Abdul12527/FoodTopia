package com.Arjunagi.Foodtopia.models;

import com.Arjunagi.Foodtopia.models.address.CustomerAddress;
import com.Arjunagi.Foodtopia.models.user.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer numberOfItems;
    private Double totalPrice;
    private OrderStatus status;
    @ManyToOne
    @JoinColumn(name = "fkCustomerAddressId")
    private CustomerAddress customerAddress;
    @ManyToMany
    @JoinTable(name = "fkFoodItemOrderTable",
            joinColumns = @JoinColumn(name = "fkOrder") ,
            inverseJoinColumns = @JoinColumn(name = "fkFoodItem"))
    private List<FoodItem> foodItemList;
    @ManyToOne
    @JoinColumn(name = "fkRestaurantId")
    private Restaurant restaurant;
}
