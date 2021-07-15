package com.pixel.meirlen.orc.presenter;


import com.pixel.meirlen.orc.base.BasePresenter;
import com.pixel.meirlen.orc.model.Category;
import com.pixel.meirlen.orc.view.CategoryView;

import java.util.List;

public interface CategoryPresenter extends BasePresenter<CategoryView> {

    void getCategories(String token);
    void getCartCount(String token);
    void getCategoriesFromLocalDb();
    void getCategoriesById(String id);
    void insertLocalDb(List<Category> categories);


}
