package com.pixel.meirlen.orc.interfaces;


import com.pixel.meirlen.orc.model.Product;
import com.pixel.meirlen.orc.model.history.History;

public interface UpdateProductListener {
    void onUpdateProduct(Product product);
    public void showLoading();
    public void hideLoading();
}
