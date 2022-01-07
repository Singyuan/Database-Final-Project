package com.lwdevelop.backend.payload.request;

import javax.validation.constraints.PositiveOrZero;

public class OrderRequest {
    @PositiveOrZero
    private Long productId;

    @PositiveOrZero
    private int amount;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
