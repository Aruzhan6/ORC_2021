package com.example.meirlen.orc.presenter;


import com.example.meirlen.orc.base.BasePresenter;
import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.model.request.Filter;
import com.example.meirlen.orc.view.CategoryView;
import com.example.meirlen.orc.view.ProductView;

import java.util.List;

public interface ProductPresenter extends BasePresenter<ProductView> {

    void getList(Filter filter);



}
