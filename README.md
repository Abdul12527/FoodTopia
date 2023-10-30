# FoodTopia: Satisfy Your Cravings with Convenience 🍔🍕

## Summary
Welcome to FoodTopia, a culinary adventure for food enthusiasts! FoodTopia, inspired by Ethiopia, brings together a delightful world of food delivery. It's your go-to app for discovering a diverse range of restaurants and mouthwatering food items. We offer a hassle-free platform for restaurant owners to showcase their culinary creations and reach a wider audience—all of this at no cost!

## Language and Framework
![Java](https://img.shields.io/badge/Language-Java-green)
![Spring Boot](https://img.shields.io/badge/Framework-Spring%20Boot-brightgreen)

## Controllers
### AppAdminController
- **POST /app/admin**
  - Add a new App Admin.
- **POST /app/admin/login**
  - Log in as an App Admin.
- **GET /app/admin/{authTokenValue}**
  - Get App Admin details.
- **GET /app/admin/{authTokenValue}/customers**
  - Get a list of all customers.
- **GET /app/admin/{authTokenValue}/restaurant/admins**
  - Get a list of all restaurant admins.
- **DELETE /app/admin/logout/{authTokenValue}**
  - Log out as an App Admin.

### CustomerAddressController
- **POST /address/customer/{authTokenValue}**
  - Add a customer address.
- **GET /addresses/customer/{authTokenValue}**
  - Get all addresses for a customer.
- **GET /address/customer/{authTokenValue}**
  - Get a customer address by ID.
- **PUT /address/customer/{authTokenValue}**
  - Update a customer address.
- **DELETE /address/customer/{authTokenValue}**
  - Delete a customer address.

### CustomerController
- **POST /customer**
  - Add a customer.
- **POST /customer/login**
  - Log in as a customer.
- **GET /customer/{authTokenValue}**
  - Get customer details.
- **DELETE /customer/logout/{authTokenValue}**
  - Log out as a customer.

### FoodItemController
- **POST /foodItem/restaurant/admin/{authTokenValue}**
  - Add a food item (for restaurant admin).
- **GET /foodItems**
  - Get a list of all food items.
- **GET /foodItem/{foodItemId}**
  - Get a food item by ID.
- **GET /foodItems/restaurant**
  - Get food items by restaurant.
- **PUT /foodItem/restaurant/admin/{authTokenValue}**
  - Update a food item (for restaurant admin).
- **DELETE /foodItem/{foodItemId}/restaurant/admin/{authTokenValue}**
  - Delete a food item (for restaurant admin).

### OrderController
- **POST /customer/order/{authTokenValue}**
  - Add an order.
- **PUT /order/{orderId}/status/preparing/restaurant/admin/{authTokenValue}**
  - Update order status to "Preparing" (for restaurant admin).
- **PUT /order/{orderId}/status/onTheWay/restaurant/admin/{authTokenValue}**
  - Update order status to "On The Way" (for restaurant admin).
- **PUT /order/{orderId}/status/delivered/customer/{authTokenValue}**
  - Update order status to "Delivered" (for customer).
- **DELETE /order/{orderId}/status/cancelled/customer/{authTokenValue}**
  - Update order status to "Cancelled" (for customer).
- **GET /orders/customer/{authTokenValue}**
  - Get all orders for a customer.
- **GET /order/customer/{authTokenValue}**
  - Get a customer order by order ID.
- **GET /orders/restaurant/admin/{authTokenValue}**
  - Get all orders for a restaurant admin.

### RestaurantAddressController
- **POST /address/restaurant/{authTokenValue}**
  - Add a restaurant address.
- **GET /addresses/restaurant/{authTokenValue}**
  - Get all addresses for a restaurant admin.
- **GET /address/restaurant/{authTokenValue}**
  - Get a restaurant address by ID.
- **PUT /address/restaurant/{authTokenValue}**
  - Update a restaurant address.
- **DELETE /address/restaurant/{authTokenValue}**
  - Delete a restaurant address.

### RestaurantAdminController
- **POST /restaurant/admin**
  - Add a restaurant admin.
- **POST /restaurant/admin/login**
  - Log in as a restaurant admin.
- **GET /restaurant/admin/{authTokenValue}**
  - Get restaurant admin details.
- **DELETE /restaurant/admin/logout/{authTokenValue}**
  - Log out as a restaurant admin.

### RestaurantController
- **POST /restaurant/{authTokenValue}**
  - Add a restaurant (for restaurant admin).
- **GET /restaurants**
  - Get a list of all restaurants.
- **GET /restaurant/{id}**
  - Get a restaurant by ID.
- **DELETE /restaurant/{authTokenValue}/{restaurantId}**
  - Delete a restaurant (for restaurant admin).
