package com.pixel.meirlen.orc.presenter;


import com.pixel.meirlen.orc.base.BasePresenter;
import com.pixel.meirlen.orc.model.Category;
import com.pixel.meirlen.orc.model.Product;
import com.pixel.meirlen.orc.model.request.Filter;
import com.pixel.meirlen.orc.view.CategoryView;
import com.pixel.meirlen.orc.view.ProductView;

import java.util.List;

public interface ProductPresenter extends BasePresenter<ProductView> {

    void getList(String token,Filter filter);

    void getListById(String category_id,String token,Filter filter);

    void getProductById(String product_id,String token);

    void getProducerById(String category_id,String token,Filter filter);

    void getFavourities(String token);

    void addCart(String token,String id,String decrement);

    void markFavourite(String token, Integer product_id);


}
