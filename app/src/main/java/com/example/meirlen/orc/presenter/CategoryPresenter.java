package com.example.meirlen.orc.presenter;


import com.example.meirlen.orc.base.BasePresenter;
import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.view.CategoryView;

import java.util.List;

public interface CategoryPresenter extends BasePresenter<CategoryView> {

    void getCategories(String token);
    void getCartCount(String token);
    void getCategoriesFromLocalDb();
    void getCategoriesById(String id);
    void insertLocalDb(List<Category> categories);


}
