// create database (sqlite) including attribute, table, ...
package com.lwdevelop.backend.models;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20)
    private String name;

    @Size(max = 40)
    private String description;

    @Size(max = 40)
    private String picture;

    @PositiveOrZero(message = "Value should zero or positive.")
    private Integer inventory;

    @PositiveOrZero(message = "Value should zero or positive.")
    private Integer price;

    private Date startSaleTime;

    private Date endSaleTime;

    public Product() {
    }

    public Product(String name, String description, String picture, Integer inventory, Integer price, Date startSaleTime, Date endSaleTime) {
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

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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