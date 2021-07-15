package com.pixel.meirlen.orc.model.history;

import com.pixel.meirlen.orc.model.Product;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Purchase {

@SerializedName("purchase_id")
@Expose
private Integer purchaseId;
@SerializedName("purchase_group_id")
@Expose
private String purchaseGroupId;
@SerializedName("purchase_product_id")
@Expose
private String purchaseProductId;
@SerializedName("purchase_count")
@Expose
private String purchaseCount;
@SerializedName("product")
@Expose
private Product product;

public Integer getPurchaseId() {
return purchaseId;
}

public void setPurchaseId(Integer purchaseId) {
this.purchaseId = purchaseId;
}

public String getPurchaseGroupId() {
return purchaseGroupId;
}

public void setPurchaseGroupId(String purchaseGroupId) {
this.purchaseGroupId = purchaseGroupId;
}

public String getPurchaseProductId() {
return purchaseProductId;
}

public void setPurchaseProductId(String purchaseProductId) {
this.purchaseProductId = purchaseProductId;
}

public String getPurchaseCount() {
return purchaseCount;
}

public void setPurchaseCount(String purchaseCount) {
this.purchaseCount = purchaseCount;
}

public Product getProduct() {
return product;
}

public void setProduct(Product product) {
this.product = product;
}

}