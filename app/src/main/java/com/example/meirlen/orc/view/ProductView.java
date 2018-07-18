package com.example.meirlen.orc.view;

import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.model.Product;

import java.util.List;


public interface ProductView extends LoadingView {

    void getList(List<Product> products);

}
