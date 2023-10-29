package com.Arjunagi.Foodtopia.services;

import com.Arjunagi.Foodtopia.models.AuthTokens.CustomerAuthToken;
import com.Arjunagi.Foodtopia.models.Dto.AddressInpDTO;
import com.Arjunagi.Foodtopia.models.address.CustomerAddress;
import com.Arjunagi.Foodtopia.models.user.Customer;
import com.Arjunagi.Foodtopia.repository.ICustomerAddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerAddressServices {
    @Autowired
    private ICustomerAddressRepo customerAddressRepo;
    @Autowired
    private CustomerAuthTokenServices customerAuthTokenServices;

    public String addCustomerAddress(AddressInpDTO addressInpDTO, String authTokenValue, String email) {
        CustomerAuthToken customerAuthToken=customerAuthTokenServices.findByValue(authTokenValue);
        if(customerAuthToken==null||!customerAuthToken.getCustomer().getEmail().equals(email))return "wrong credentials";
        CustomerAddress customerAddress=new CustomerAddress(null,addressInpDTO.getStreet(),addressInpDTO.getCity(),addressInpDTO.getState(),addressInpDTO.getCountry(),addressInpDTO.getIsPrimary(),false,customerAuthToken.getCustomer());
        customerAddressRepo.save(customerAddress);
        return "address added sucessfully";
    }

    public List<CustomerAddress> getAllAddressForCustomer(String authTokenValue, String email) {
        CustomerAuthToken customerAuthToken=customerAuthTokenServices.findByValue(authTokenValue);
        if(customerAuthToken==null||!customerAuthToken.getCustomer().getEmail().equals(email))return null;
        return customerAddressRepo.findAllByCustomerAndDeleted(customerAuthToken.getCustomer(),false);
    }

    public CustomerAddress getAddressById(String authTokenValue, String email, Integer addressId) {
        CustomerAuthToken customerAuthToken=customerAuthTokenServices.findByValue(authTokenValue);
        if(customerAuthToken==null||!customerAuthToken.getCustomer().getEmail().equals(email))return null;
        CustomerAddress customerAddress=customerAddressRepo.findById(addressId).orElseThrow();
        if(!customerAddress.getCustomer().equals(customerAuthToken.getCustomer())||customerAddress.getDeleted())return null;
        return customerAddress;
    }

    public String updateAddress(AddressInpDTO addressInpDTO, String authTokenValue, String email,Integer addressId) {
        CustomerAuthToken customerAuthToken=customerAuthTokenServices.findByValue(authTokenValue);
        if(customerAuthToken==null||!customerAuthToken.getCustomer().getEmail().equals(email))return "wrong credentials";
        CustomerAddress currentAddress=customerAddressRepo.findById(addressId).orElseThrow();
        if(!currentAddress.getCustomer().equals(customerAuthToken.getCustomer()))return "un-autherized access";
        currentAddress.setDeleted(true);
        customerAddressRepo.save(currentAddress);
        CustomerAddress customerAddress=new CustomerAddress(null,addressInpDTO.getStreet(),addressInpDTO.getCity(),addressInpDTO.getState(),addressInpDTO.getCountry(),addressInpDTO.getIsPrimary(),false,customerAuthToken.getCustomer());
        customerAddressRepo.save(customerAddress);
        return "updated sucessfully";
    }

    private void makeCurrentPrimaryFalse(Customer customer) {
        CustomerAddress customerAddress=customerAddressRepo.findByCustomerAndIsPrimary(customer,true);
        if(customerAddress!=null&&!customerAddress.getDeleted()) {
            customerAddress.setIsPrimary(false);
            customerAddressRepo.save(customerAddress);
        }
    }

    public String deleteAddress(String authTokenValue, String email, Integer addressId) {
        CustomerAuthToken customerAuthToken=customerAuthTokenServices.findByValue(authTokenValue);
        if(customerAuthToken==null||!customerAuthToken.getCustomer().getEmail().equals(email))return "wrong credentials";
        CustomerAddress customerAddress=customerAddressRepo.findById(addressId).orElseThrow();
        if(!customerAddress.getCustomer().equals(customerAuthToken.getCustomer()))return "un-autherized access";
        customerAddress.setDeleted(true);
        customerAddressRepo.save(customerAddress);
        return "deleted sucessfully";
    }

    public CustomerAddress getCustomerPirmaryAddress(Customer customer) {
        return customerAddressRepo.findByCustomerAndIsPrimary(customer,true);
    }
}
