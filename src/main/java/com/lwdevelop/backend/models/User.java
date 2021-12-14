// create database (sqlite) including attribute, table, ...
package com.lwdevelop.backend.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20)
    private String name;

    @Size(max = 80)
    private String email;

    @Size(max = 20)
    private String password;

    @Size(max = 20)
    private String phone;

    @Size(max = 10)
    private String type;

    public User() {
    }

    public User(String name, String email, String password, String phone, String type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    public String getName() {
        return name;
    }

    // public void setName(String name) {
    //     this.name = name;
    // }

    public String getEmail() {
        return email;
    }

    // public void setEmail(String email) {
    //     this.email = email;
    // }

    public String getPassword() {
        return password;
    }

    // public void setPassword(String password){
    //     this.password = password;
    // }

    public String getPhone() {
        return email;
    }

    // public void setPhone(String phone) {
    //     this.phone = phone;
    // }

    public String getType() {
        return email;
    }

    // public void setType(String type) {
    //     this.type = type;
    // }
}