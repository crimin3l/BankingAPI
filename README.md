An API to be used for opening a new “current account” of already existing customers.

• The API will expose an endpoint which accepts the user information (customerID, initialCredit). 
• Once the endpoint is called, a new account will be opened connected to the user whose ID is customerID. 
• Also, if initialCredit is not 0, a transaction will be sent to the new account. 
• Another Endpoint will output the user information showing Name, Surname, balance, and transactions of the accounts. 

To run the project:

1. Clone the github repo

2. Run tests

3. Run Spring Boot project

4. Use Postman or curl to make a new Account (Post request to /api/v1/account/createNewAccount)
Ex: Post verb to localhost:8080/api/v1/account/createNewAccount 
   {
   "customerId":1,
   "initialCredit": 44.54
   }

   will create a new account for customer with id 1 and initialCredit of 44.54. You can also use negative values like -2.4 
   If the initialCredit is not 0 the account will be credited with the initialCredit.

   By default, there are 3 customers already crated for demonstration purposes. The preconfigured customers are loaded via data.sql script.
   The default customers have id 1, 2 and 3. The API does not exposes nor it requires at this point a way to create new customers but this feature can be easily implemented by exposing a new endpoint linked to createNewCustomer method in CustomerServiceImpl.

5. Use Postman or curl  to get info about an existing customer by customerId. The endpoint will display the customer name, surname, account list and total balance of the accounts
Ex: Get verb to localhost:8080/api/v1/statement/getStatementByCustomerId?customerId=1
   {
   "customerId": 1
   }
   will output based on the CustomerId taken as input, the name, surname, accounts and total balance of the accounts.
   Endpoint will output http status code 200 (OK) if the customer is existent and code 204 (No Content) if the customer id is non-existent.

(Prefix all Postman calls with Host and port. Default Host is localhost, default port is 8080.)
run tests:
mvn clean test

run spring boot project:
mvn install && mvn spring-boot:run
