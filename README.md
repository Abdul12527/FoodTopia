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
    
## Services
### AppAdminAuthTokenServices

- Generates authentication tokens for App Admins.
- Provides the ability to find an App Admin by token value.
- Allows for the deletion of authentication tokens.

### AppAdminServices

- Manages App Admins and their authentication.
- Enables the addition of a new App Admin.
- Supports logging in and out of App Admin accounts.
- Provides methods for getting App Admin details, all customers, and all restaurant admins associated with an App Admin.

### CustomerAddressServices

- Handles customer addresses and their operations.
- Allows adding customer addresses for authenticated customers.
- Supports retrieving all addresses for a customer.
- Provides the ability to get a specific customer address by ID.
- Enables updating, making addresses primary, and deleting customer addresses.

### CustomerAuthTokenServices

- Manages authentication tokens for Customers.
- Generates new tokens for Customers.
- Provides methods for finding a token by value, deleting a token, and finding a token by the associated Customer.

### CustomerServices

- Manages Customer accounts and authentication.
- Supports adding new Customers.
- Provides login and logout functionality for Customers.
- Allows fetching Customer details based on authentication tokens.

### FoodItemServices

- Manages food items for restaurants.
- Supports adding new food items.
- Retrieves a list of all food items.
- Allows for finding a food item by ID, getting food items by restaurant, updating, and deleting food items.

### OrderServices

- Handles customer orders and order-related operations.
- Supports placing new orders.
- Enables updating order status for both customers and restaurant admins.
- Provides methods for getting customer orders, customer order details, and all orders for restaurant admins.

### RestaurantAddressServices

- Manages restaurant addresses and their operations.
- Allows adding restaurant addresses for authenticated restaurant admins.
- Supports retrieving all addresses for a restaurant admin.
- Provides the ability to get a specific restaurant address by ID.
- Enables updating and deleting restaurant addresses.

### RestaurantAdminAuthTokenServices

- Generates authentication tokens for Restaurant Admins.
- Provides the ability to find a Restaurant Admin by token value.
- Allows for the deletion of authentication tokens.

### RestaurantAdminServices

- Manages Restaurant Admin accounts and their authentication.
- Supports adding new Restaurant Admins.
- Provides login and logout functionality for Restaurant Admins.
- Allows fetching Restaurant Admin details based on authentication tokens.

### RestaurantServices

- Manages restaurant-related operations.
- Supports adding new restaurants.
- Retrieves a list of all restaurants.
- Allows for finding a restaurant by ID, deleting restaurants, and finding a restaurant by associated Restaurant Admin.

## Repositories
 **IAppAdminAuthTokenRepo**
   - Repository for managing authentication tokens for App Admins.
   - Provides methods to find tokens by their values.

 **IAppAdminRepo**
   - Repository for managing App Admins.
   - Provides methods to find App Admins by email.

 **ICustomerAddressRepo**
   - Repository for managing Customer addresses.
   - Offers methods to retrieve all addresses for a Customer, find a primary address, and filter addresses by deleted status.

 **ICustomerAuthTokenRepo**
   - Repository for managing authentication tokens for Customers.
   - Provides methods to find tokens by their values and by associated Customers.

 **ICustomerRepo**
   - Repository for managing Customers.
   - Provides methods to find Customers by email.

 **IFoodItemRepo**
   - Repository for managing Food Items.
   - Allows fetching Food Items associated with a specific Restaurant.

 **IOrderRepo**
   - Repository for managing customer orders.
   - Provides methods to find orders associated with a Customer, Restaurant Admin, and filter orders by status.

 **IRestaurantAddressRepo**
   - Repository for managing Restaurant addresses.
   - Offers methods to retrieve addresses for a Restaurant Admin and filter by deleted status.

 **IRestaurantAdminAuthTokenRepo**
   - Repository for managing authentication tokens for Restaurant Admins.
   - Provides methods to find tokens by their values.

 **IRestaurantAdminRepo**
    - Repository for managing Restaurant Admins.
    - Provides methods to find Restaurant Admins by email.

 **IRestaurantRepo**
    - Repository for managing Restaurants.
    - Offers a method to find a Restaurant by its associated Restaurant Admin.

## Database Sceha
**Here I have used MySQL database **
