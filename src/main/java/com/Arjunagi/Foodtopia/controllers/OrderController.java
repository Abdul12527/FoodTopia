package com.Arjunagi.Foodtopia.controllers;

import com.Arjunagi.Foodtopia.models.CustomerOrder;
import com.Arjunagi.Foodtopia.models.Dto.OrderInputDto;
import com.Arjunagi.Foodtopia.models.OrderStatus;
import com.Arjunagi.Foodtopia.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderServices orderServices;
    @PostMapping("customer/order/{authTokenValue}")
    public String addOrder(@RequestBody OrderInputDto orderInputDto, @PathVariable String authTokenValue, @RequestParam String email){
        return orderServices.addOrder(orderInputDto,authTokenValue,email);
    }
    @PutMapping("order/{orderId}/status/preparing/restaurant/admin/{authTokenValue}")
    public String updateOrderStatusToPreparing(@PathVariable Integer orderId,@PathVariable String authTokenValue,@RequestParam String email){
        return orderServices.updateOrderStatusToPreparing(orderId,authTokenValue,email,OrderStatus.PREPARING);
    }
    @PutMapping("order/{orderId}/status/onTheWay/restaurant/admin/{authTokenValue}")
    public String updateOrderStatusToOnTheWay(@PathVariable Integer orderId,@PathVariable String authTokenValue,@RequestParam String email){
        return orderServices.updateOrderStatusToPreparing(orderId,authTokenValue,email, OrderStatus.ON_THE_WAY);
    }
    @PutMapping("order/{orderId}/status/delivered/customer/{authTokenValue}")
    public String updateOrderStatusToDelivered(@PathVariable Integer orderId,@PathVariable String authTokenValue,@RequestParam String email){
        return orderServices.updateOrderStatusToDeliveredOrCancelled(orderId,authTokenValue,email, OrderStatus.DELIVERED);
    }
    @DeleteMapping("order/{orderId}/status/cancelled/customer/{authTokenValue}")
    public String updateOrderStatusToCancelled(@PathVariable Integer orderId,@PathVariable String authTokenValue,@RequestParam String email){
        return orderServices.updateOrderStatusToDeliveredOrCancelled(orderId,authTokenValue,email, OrderStatus.CANCELLED);
    }
    @GetMapping("orders/customer/{authTokenValue}")
    public List<CustomerOrder> getAllOrdersByCustomer(@PathVariable String authTokenValue,@RequestParam String email){
        return orderServices.getAllOrdersByCustomer(authTokenValue,email);
    }
    @GetMapping("order/customer/{authTokenValue}")
    public CustomerOrder getCustomerOrderByOrderId(@PathVariable String authTokenValue,@RequestParam String email,@RequestParam Integer orderId){
        return orderServices.getCustomerOrderByOrderId(authTokenValue,email,orderId);
    }
    @GetMapping("orders/restaurant/admin/{authTokenValue}")
    public List<CustomerOrder> getAllOrdersByRestaurant(@PathVariable String authTokenValue,@RequestParam String email){
        return orderServices.getAllOrdersByRestaurant(authTokenValue,email);
    }

}
