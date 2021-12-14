// data format for json
package com.lwdevelop.backend.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.sql.Date;

public class UserRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @NotBlank
    @Size(min = 3, max = 80)
    private String email;

    @NotBlank
    @Size(min = 3, max = 20)
    private String password;

    @NotBlank
    @Size(min = 3, max = 20)
    private String phone;

    // @NotBlank
    @Size(min = 0, max = 10)
    private String type;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // public String getPicture() {
    //     return picture;
    // }

    // public void setPicture(String picture) {
    //     this.picture = picture;
    // }

    // public int getInventory() {
    //     return inventory;
    // }

    // public void setInventory(int inventory) {
    //     this.inventory = inventory;
    // }

    // public int getPrice() {
    //     return price;
    // }

    // public void setPrice(int price) {
    //     this.price = price;
    // }

    // public Date getStartSaleTime() {
    //     return startSaleTime;
    // }

    // public void setStartSaleTime(Date startSaleTime) {
    //     this.startSaleTime = startSaleTime;
    // }

    // public Date getEndSaleTime() {
    //     return endSaleTime;
    // }

    // public void setEndSaleTime(Date endSaleTime) {
    //     this.endSaleTime = endSaleTime;
    // }
}
