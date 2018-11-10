package com.example.meirlen.orc.interfaces;


import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.model.history.History;

public interface UpdateProductListener {
    void onUpdateProduct(Product product);
    public void showLoading();
    public void hideLoading();
}
