// data format for json
package com.lwdevelop.backend.payload.request;

public class OrderRequest {    

    private Long productID;

    private int amount;


    public Long getProductID() {
        return productID;
    }

    // public void setProductID(int productID) {
    //     this.productID = productID;
    // }

    public int getAmount() {
        return amount;
    }

    // public void setAmount(int amount) {
    //     this.amount = amount;
    // }
}
