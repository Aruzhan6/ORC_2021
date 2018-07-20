package com.example.meirlen.orc.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartRequest {

@SerializedName("product_id")
@Expose
private Integer productId;
@SerializedName("decrement")
@Expose
private Integer decrement;

public Integer getProductId() {
return productId;
}

public void setProductId(Integer productId) {
this.productId = productId;
}

public Integer getDecrement() {
return decrement;
}

public void setDecrement(Integer decrement) {
this.decrement = decrement;
}

}