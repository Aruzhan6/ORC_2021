package com.pixel.meirlen.orc.model;

import android.arch.persistence.room.Ignore;

import java.io.Serializable;
import java.util.List;

import com.pixel.meirlen.orc.model.discount.Discount;
import com.pixel.meirlen.orc.model.filter.FieldValues;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product implements Serializable {

    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("product_category_id")
    @Expose
    private String productCategoryId;
    @SerializedName("product_producer_id")
    @Expose
    private Integer productProducerId;
    @SerializedName("total_price")
    @Expose
    private Double total_price;

    @SerializedName("discount")
    @Expose
    private Discount discount;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_description")
    @Expose
    private String productDescription;
    @SerializedName("product_price")
    @Expose
    private Integer productPrice;
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
    private String cartCount;

    @SerializedName("isFavourite")
    @Expose
    private Boolean isFavourite = false;

    @SerializedName("product_weight")
    @Expose
    private String productWeight;


    @SerializedName("product_size")
    @Expose
    private String productSize;

    @SerializedName("images")
    @Expose
    private List<ProductImage> images = null;


    @SerializedName("producer")
    @Expose
    private Producer producer = null;


    @SerializedName("fields_and_values")
    @Expose
    private List<FieldValues> fields_and_values = null;



    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Integer getProductProducerId() {
        return productProducerId;
    }

    public void setProductProducerId(Integer productProducerId) {
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

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
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

    public String getCartCount() {
        return cartCount;
    }

    public void setCartCount(String cartCount) {
        this.cartCount = cartCount;
    }


    public Boolean getFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }



    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }


    public List<FieldValues> getFields_and_values() {
        return fields_and_values;
    }

    public void setFields_and_values(List<FieldValues> fields_and_values) {
        this.fields_and_values = fields_and_values;
    }
}