# Instructions of Database System Final Project 
## Table of Content
<!-- TABLE OF CONTENTS -->
1. [Introduction](#introduction)
2. [Quick start](#quick-start)
3. [Details of all Functions](#details-of-all-functions)
3. [Acknowledgements](#acknowledgements)
3. [Other Materials](#other-materials)

## Introduction
3-tier architecture.
![](https://i.imgur.com/1brucSh.png)
The following is my entity-relation model
![](https://i.imgur.com/ixUvMpd.png)
* A user can be seller or buyer or both.
* User can create many orders
* One order is divided by many order details, each of which is corresponding to distict product.
* Many order detail may corresponding to same prduct.


## Quick start

#### First step on DB
```sql
INSERT INTO type(name) VALUES('ROLE_BUYER');
INSERT INTO type(name) VALUES('ROLE_SELLER');
```
1. **Create Seller Account**
    select `Post`.
     ```
     http://localhost:8080/api/users
     ```
     ```json
     {
        "name" : "seller0",
        "email" : "seller0@gmail.com",
        "password" : "123456",
        "phone" : "0988882566",
        "type" : ["seller"]
    }
     ```
2. **Login**
    select `Post`.
      ```
     http://localhost:8080/api/users/signin
     ```
     ```json
     {
        "email" : "seller0@gmail.com",
        "password" : "12345"
    }
    ```
    
3. **Upload an Image**
    select `Post`.
    ```
    http://localhost:8080/api/images
    ```
    1. choose `form-data` and choose `file` in column `key` and upload your file in column `value`.
    2. Copy the url and paste to the field `picture` in product .
    
4. **Create product**
    paste the token and paste the picture url, select `Post`.
    ```
     http://localhost:8080/api/products
     ```
     ```json
    {
        "name": "product1",
        "description": "xxxxxx",
        "picture": "src/main/resources/static/xxx.jpg",
        "inventory": 5,
        "price": 200,
        "startSaleTime": "2021-06-01",
        "endSaleTime": "2022-07-01"
    }
    ```


5. **Create buyer account**
    select `Post`.
     ```
     http://localhost:8080/api/users
     ```
     ```json
    {
        "name" : "buyer0",
        "email" : "buyer0@gmail.com",
        "password" : "123456",
        "phone" : "0988882566"
    }
     ```
 6. **Login**
     select `Post`.
     ```
     http://localhost:8080/api/users/signin
     ```
     ```json
    {
        "email" : "buyer0@gmail.com",
        "password" : "123456"
    }
    ```

7. **Search products**
    paste the token and select `Get`.
    ```
    http://localhost:8080/api/products
     ```

8. **Create a order**
    paste the token, and select `Post`.
    ```
    http://localhost:8080/api/orders
     ```
     ```json
    [
        {
            "productId" : 1,
            "amount" : 5
        }
    ]
    ```
    
9. **See all orders**
    paste the token, and select `Get`.
    ```
    http://localhost:8080/api/orders
     ```

## Details of all Functions
### User
* **Create user** `/user`
    As quick start. User can choose his role in attribute `type`, buyer, seller or both.
    > Fool-Proof Design: The account exist
    > Leave the messenge "Email is already in use!"
    
* **Update user** `/user/{id}`
    paste the token
    ```json
    {
    "name" : "seller0",
    "phone" : "0988882111",
    }
    ```
    > * If user id doesn't exist, 500 Internal Server Error
    > * If field is empty, 400 bad request.
    
* **Sign in** `/user/singin`
    As quick start.
    > If account doesn't not exist or password is wrong, 401 unauthorized
    
* **Get self data** `/user/me`
    paste the token
    > If token is wrong, 401 unauthorized
    

### Product
* **Create the product** `/products`
    As quick start.
    > If one is buyer, 403 forbidden
    
* **Get all products** `/products`
    paste the token

* **Get the products** `/products/{id}`
    paste the token

* **Update the products** `/products/{id}`
    paste the seller token
    ```json
    {
    "name": "product1",
    "description": "xxxxxx",
    "picture": "zzzzz",
    "inventory": 5,
    "price": 200,
    "startSaleTime": "2021-06-01",
    "endSaleTime": "2022-07-01"
    }
    ```
    > * If one is buyer, 403 forbidden
    > * If out of product id, 500 internal server error.
    
* **Delete the product** `/products/{id}`
    paste the seller token
    > * If one is buyer, 403 forbidden
    > * If out of product id, 500 internal server error.
    
### Order
* **Create order** `/orders`
    paste the seller token
    ```json
    [
        {
            "productId" : 1,
            "amount" : 20
        },
        {
            "productId" : 2,
            "amount" : 10
        }
    ]
    ```
    > If out of product id, 500 internal server error.
    
* **Get all order** `/orders`
    paste the seller token
    > If one is buyer, 403 forbidden
    
* **Get the order** `/orders/{id}`
    paste the token
    > If out of product id, 500 internal server error.
    
### Image
* **Upload an Image** `/images`
    1. choose `form-data` and choose `file` in column `key` and upload your file in column `value`.
    2. Copy the url and paste to the attribute `picture` in product .
    > If one is buyer, 403 forbidden.
	> spoiler Fool-Proof Design: Image miss
    > Message: Please select a file!

## Acknowledgements
I wishes to acknowledge the help of my friend Zoe Chen in developing this project.

## Other Materials
* [Note](https://hackmd.io/@singyuan/S1r5VWU3t)

* [Video](https://drive.google.com/file/d/1WqrCCEzNU6GRqZi-u6ipw9IxAWj13xzS/view?usp=sharing)