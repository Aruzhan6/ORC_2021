package com.example.meirlen.orc.view;

import com.example.meirlen.orc.model.CardResponse;
import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.model.DetailResponse;
import com.example.meirlen.orc.model.Product;

import java.util.List;


public interface ProductView extends LoadingView {

    void getList(List<Product> products);

    void addCartResponse(CardResponse response);

    void detailResponse(DetailResponse response);

    void showItemLoading(int requestCode);

    void hideItemLoading(int requestCode);

    void markFavourite(Product response);
}
