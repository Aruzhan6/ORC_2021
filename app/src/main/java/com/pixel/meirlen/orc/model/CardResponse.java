package com.pixel.meirlen.orc.model;

import com.pixel.meirlen.orc.model.discount.Discount;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardResponse {

    @SerializedName("cart_id")
    @Expose
    private String cartId;
    @SerializedName("cart_user_id")
    @Expose
    private String cartUserId;
    @SerializedName("cart_product_id")
    @Expose
    private String cartProductId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("cart_count")
    @Expose
    private Integer cartCount;
    @SerializedName("total_price")
    @Expose
    private Double totalPrice;
    @SerializedName("product")
    @Expose
    private Product product;

    @SerializedName("discount")
    @Expose
    private Discount discount;

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getCartUserId() {
        return cartUserId;
    }

    public void setCartUserId(String cartUserId) {
        this.cartUserId = cartUserId;
    }

    public String getCartProductId() {
        return cartProductId;
    }

    public void setCartProductId(String cartProductId) {
        this.cartProductId = cartProductId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getCartCount() {
        return cartCount;
    }

    public void setCartCount(Integer cartCount) {
        this.cartCount = cartCount;
    }



    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}