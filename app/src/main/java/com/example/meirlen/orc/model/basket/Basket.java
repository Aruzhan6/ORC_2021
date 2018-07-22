package com.example.meirlen.orc.model.basket;

import com.example.meirlen.orc.model.Product;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Basket {

    @SerializedName("cart_id")
    @Expose
    private Integer cartId;
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
    private String cartCount;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("product_category_id")
    @Expose
    private String productCategoryId;
    @SerializedName("product_producer_id")
    @Expose
    private String productProducerId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_description")
    @Expose
    private String productDescription;
    @SerializedName("product_price")
    @Expose
    private String productPrice;
    @SerializedName("total_price")
    @Expose
    private Integer totalPrice;


    @SerializedName("product")
    @Expose
    private Product product;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
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

    public String getCartCount() {
        return cartCount;
    }

    public void setCartCount(String cartCount) {
        this.cartCount = cartCount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductProducerId() {
        return productProducerId;
    }

    public void setProductProducerId(String productProducerId) {
        this.productProducerId = productProducerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}