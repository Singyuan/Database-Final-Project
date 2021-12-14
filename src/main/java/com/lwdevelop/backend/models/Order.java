// create/update database (sqlite) including attribute, table, ...
package com.lwdevelop.backend.models;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Size(max = 20)
    private Long productID;

    // @Size(max = 80)
    private int amount;


    public Order() {
    }

    public Order(Long productID, int amount) {
        this.productID = productID;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    public Long getProductID() {
        return productID;
    }

    // public void setProductID(Long productID) {
    //     this.productID = productID;
    // }

    public int getAmount() {
        return amount;
    }

    // public void setAmount(int amount) {
    //     this.amount = amount;
    // }
}