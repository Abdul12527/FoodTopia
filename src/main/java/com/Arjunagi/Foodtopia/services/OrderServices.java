package com.Arjunagi.Foodtopia.services;

import com.Arjunagi.Foodtopia.models.AuthTokens.CustomerAuthToken;
import com.Arjunagi.Foodtopia.models.AuthTokens.RestaurantAdminAuthToken;
import com.Arjunagi.Foodtopia.models.CustomerOrder;
import com.Arjunagi.Foodtopia.models.Dto.OrderInputDto;
import com.Arjunagi.Foodtopia.models.FoodItem;
import com.Arjunagi.Foodtopia.models.OrderStatus;
import com.Arjunagi.Foodtopia.models.Restaurant;
import com.Arjunagi.Foodtopia.models.address.CustomerAddress;
import com.Arjunagi.Foodtopia.repository.IOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServices {
    @Autowired
    private IOrderRepo orderRepo;
    @Autowired
    private CustomerAuthTokenServices customerAuthTokenServices;
    @Autowired
    private RestaurantServices restaurantServices;
    @Autowired
    private FoodItemServices foodItemServices;
    @Autowired
    private CustomerAddressServices customerAddressServices;
    @Autowired
    private RestaurantAdminAuthTokenServices restaurantAdminAuthTokenServices;

    public String addOrder(OrderInputDto orderInputDto, String authTokenValue, String email) {
        CustomerAuthToken customerAuthToken=customerAuthTokenServices.findByValue(authTokenValue);
        if(authTokenValue==null||!customerAuthToken.getCustomer().getEmail().equals(email))return "un-autherized access";
        CustomerOrder order=new CustomerOrder();
        if(orderInputDto.getCustomerAddressId()!=null){
            CustomerAddress customerAddress=customerAddressServices.getAddressById(authTokenValue,email,orderInputDto.getCustomerAddressId());
            if(customerAddress==null)return "please added the address there is no primary address";
            order.setCustomerAddress(customerAddress);
        }else {
            order.setCustomerAddress(customerAddressServices.getCustomerPirmaryAddress(customerAuthToken.getCustomer()));
        }
        List<FoodItem> foodItemList=new ArrayList<>();
        Restaurant restaurant=null;
        Double total=0.0;
        for(var i:orderInputDto.getFoodItemsIds()){
            FoodItem foodItem=foodItemServices.getFoodItemById(i);
            if(foodItem==null)return "please select proper food items";
            if(restaurant==null)restaurant=foodItem.getRestaurant();
            if(!foodItem.getRestaurant().equals(restaurant))return "all food items must be from same restaurant";
            total+=foodItem.getPrice();
            foodItemList.add(foodItem);
        }
        List<CustomerOrder> ongoing=orderRepo.findAllByCustomerAddress_CustomerAndRestaurantAndStatusNotAndStatusNot(customerAuthToken.getCustomer(),restaurant,OrderStatus.DELIVERED,OrderStatus.CANCELLED);
        System.out.println("-----------------------"+ongoing);
        if(ongoing.size()!=0)return "wait till current order is delivered";
        order.setFoodItemList(foodItemList);
        order.setRestaurant(restaurant);
        order.setNumberOfItems(foodItemList.size());
        order.setTotalPrice(total);
        order.setStatus(OrderStatus.PLACED);
        orderRepo.save(order);
        return "order placed sucessfully";
    }

    public String updateOrderStatusToPreparing(Integer orderId, String authTokenValue, String email,OrderStatus orderStatus) {
        RestaurantAdminAuthToken restaurantAdminAuthToken=restaurantAdminAuthTokenServices.findByValue(authTokenValue);
        if(restaurantAdminAuthToken==null||!restaurantAdminAuthToken.getRestaurantAdmin().getEmail().equals(email))return "un-autherized access";
        CustomerOrder order=orderRepo.findById(orderId).orElseThrow();
        Restaurant restaurant=restaurantServices.findByRestaurantAdmin(restaurantAdminAuthToken.getRestaurantAdmin());
        if(restaurant==null||!restaurant.getRestaurantAdmin().equals(restaurantAdminAuthToken.getRestaurantAdmin()))return "un-autherized access";
        if(order.getStatus().equals(OrderStatus.CANCELLED)||order.getStatus().equals(OrderStatus.DELIVERED))return "completed order";
        order.setStatus(orderStatus);
        orderRepo.save(order);
        return "updated sucessfully";
    }

    public String updateOrderStatusToDeliveredOrCancelled(Integer orderId, String authTokenValue, String email, OrderStatus delivered) {
        CustomerAuthToken customerAuthToken=customerAuthTokenServices.findByValue(authTokenValue);
        if(authTokenValue==null||!customerAuthToken.getCustomer().getEmail().equals(email))return "un-autherized access";
        CustomerOrder order=orderRepo.findById(orderId).orElseThrow();
        if(!order.getCustomerAddress().getCustomer().equals(customerAuthToken.getCustomer()))return "un-autherized access";
        if(order.getStatus().equals(OrderStatus.CANCELLED)||order.getStatus().equals(OrderStatus.DELIVERED))return "completed order";
        order.setStatus(delivered);
        orderRepo.save(order);
        return "status Updated sucessfully";
    }

    public List<CustomerOrder> getAllOrdersByCustomer(String authTokenValue, String email) {
        CustomerAuthToken customerAuthToken=customerAuthTokenServices.findByValue(authTokenValue);
        if(authTokenValue==null||!customerAuthToken.getCustomer().getEmail().equals(email))return null;
        return orderRepo.findAllByCustomerAddress_Customer(customerAuthToken.getCustomer());
    }

    public CustomerOrder getCustomerOrderByOrderId(String authTokenValue, String email, Integer orderId) {
        CustomerAuthToken customerAuthToken=customerAuthTokenServices.findByValue(authTokenValue);
        if(authTokenValue==null||!customerAuthToken.getCustomer().getEmail().equals(email))return null;
        CustomerOrder order=orderRepo.findById(orderId).orElseThrow();
        if(!order.getCustomerAddress().getCustomer().equals(customerAuthToken.getCustomer()))return null;
        return order;
    }

    public List<CustomerOrder> getAllOrdersByRestaurant(String authTokenValue, String email) {
        RestaurantAdminAuthToken restaurantAdminAuthToken=restaurantAdminAuthTokenServices.findByValue(authTokenValue);
        if(restaurantAdminAuthToken==null||!restaurantAdminAuthToken.getRestaurantAdmin().getEmail().equals(email))return null;
        return orderRepo.findAllByRestaurant_RestaurantAdmin(restaurantAdminAuthToken.getRestaurantAdmin());
    }
}
