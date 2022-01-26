Project totally has 6 microservices - 3 functional and 3 non-functional. The functional were broken down from the given monolith with the project. A high-level architecture / interactions diagram was created and placed in the zip (MS Architecture.pdf).

Functional:
1) Account Service:
   - Application Name: ACCOUNT-SERVICE
   - Database: MySQL
   - Entities: Customer, Order
   - Controller has 3 REST endpoints.
     i) POST /users/login for authenticating the user based on username and password (Password is hashed using SHA-256)
     ii) POST /users/create for registering new customer
     iii) POST /users/{customerId}/order. This will be called by the ORDER-SERVICE once an order is created to store the order in the bounded context of ACCOUNT-SERVICE

2) Catalog Service:
   - Application Name: CATALOG-SERVICE
   - Database: MySQL
   - Entities: Product, Category
   - Controller has 4 different REST GET endpoints for getting product(s) information based on various inputs (product ID, category desc etc.,).
     It also has 1 GET extra endpoint which is called by the ORDER-SERVICE for getting the price while calculating order/cart total.

3) Order Service:
   - Application Name: ORDER-SERVICE
   - Database: MongoDB
   - Documents: Order, Cart
   - Controller has 2 REST endpoints.
     i) POST /orders/{customerId}/cart for adding items to the customer's cart, while calculating the total amount. It internally calls CATALOG-SERVICE endpoint for getting price details.
     ii) POST /orders/{customerId}/order for saving the order with NEW status, with data from the customer's current cart.

Non-Functional:
4) API Gateway:
   Implemented using Spring Cloud Gateway. Has 3 routes and all the routes are having Resilience4j circuit breakers added to the filters.
5) Eureka Discovery Server:
   For service registration and discovery
6) Cloud Config Server:
   Using Spring Cloud Config Server for externalizing properties


* Gateway and all the 3 functional services were also added with Spring Boot Actuator to enable auto-refresh of some properties by Spring Cloud Bus (via Rabbit MQ), Cloud Config Server and GitHub Webhook.

* Gateway and all the 3 functional services are also added Zipkin and Sleuth Maven dependencies to enable distributed tracing and are auto-configured.

* Unit test cases were written for service, repository and controller layers in ACCOUNT-SERVICE.

* All the services were also added with Spotify's Dockerfile Maven plugin and a Dockerfile for building container images and pushing to Docker Hub.
