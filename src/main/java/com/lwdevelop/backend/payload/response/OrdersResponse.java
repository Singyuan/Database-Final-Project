package com.lwdevelop.backend.payload.response;

import java.sql.Date;
import java.util.List;

public class OrdersResponse {
    private int status;
    private String message;
    private List<Order> orders;

    public static class Order {
        private Long id;
        private String buyerName;
        private String buyerEmail;
        private String buyerPhone;
        private Date timestamp;
        private List<Product> products;

        public static class Product {
            private String name;
            private String description;
            private String picture;
            private int price;
            private int amount;

            public Product(String name, String description, String picture, int price, int amount) {
                this.name = name;
                this.description = description;
                this.picture = picture;
                this.price = price;
                this.amount = amount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }
        }

        public Order(Long id, String buyerName, String buyerEmail, String buyerPhone, Date timestamp, List<Product> products) {
            this.id = id;
            this.buyerName = buyerName;
            this.buyerEmail = buyerEmail;
            this.buyerPhone = buyerPhone;
            this.timestamp = timestamp;
            this.products = products;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getBuyerName() {
            return buyerName;
        }

        public void setBuyerName(String buyerName) {
            this.buyerName = buyerName;
        }

        public String getBuyerEmail() {
            return buyerEmail;
        }

        public void setBuyerEmail(String buyerEmail) {
            this.buyerEmail = buyerEmail;
        }

        public String getBuyerPhone() {
            return buyerPhone;
        }

        public void setBuyerPhone(String buyerPhone) {
            this.buyerPhone = buyerPhone;
        }

        public Date getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Date timestamp) {
            this.timestamp = timestamp;
        }

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }
    }

    public OrdersResponse() {
    }

    public OrdersResponse(int status, String message, List<Order> orders) {
        this.status = status;
        this.message = message;
        this.orders = orders;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
