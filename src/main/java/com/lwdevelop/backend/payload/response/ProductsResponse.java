package com.lwdevelop.backend.payload.response;

import java.sql.Date;
import java.util.List;

public class ProductsResponse {
    private int status;
    private String message;
    private List<Product> products;

    public static class Product {
        private Long id;
        private String name;
        private String description;
        private String picture;
        private int inventory;
        private int price;
        private Date startSaleTime;
        private Date endSaleTime;

        public Product(Long id, String name, String description, String picture, int inventory, int price, Date startSaleTime, Date endSaleTime) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.picture = picture;
            this.inventory = inventory;
            this.price = price;
            this.startSaleTime = startSaleTime;
            this.endSaleTime = endSaleTime;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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

        public int getInventory() {
            return inventory;
        }

        public void setInventory(int inventory) {
            this.inventory = inventory;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public Date getStartSaleTime() {
            return startSaleTime;
        }

        public void setStartSaleTime(Date startSaleTime) {
            this.startSaleTime = startSaleTime;
        }

        public Date getEndSaleTime() {
            return endSaleTime;
        }

        public void setEndSaleTime(Date endSaleTime) {
            this.endSaleTime = endSaleTime;
        }
    }

    public ProductsResponse(int status, String message, List<Product> products) {
        this.status = status;
        this.message = message;
        this.products = products;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
