package com.pixel.meirlen.orc.presenter;


import com.pixel.meirlen.orc.base.BasePresenter;
import com.pixel.meirlen.orc.model.Category;
import com.pixel.meirlen.orc.model.Field;
import com.pixel.meirlen.orc.model.SearchValue;
import com.pixel.meirlen.orc.view.CategoryView;
import com.pixel.meirlen.orc.view.FieldView;

import java.util.List;

public interface FieldPresenter extends BasePresenter<FieldView> {

    void getFields(String id);
    void getFieldValues(String id);
    void updateSearchValue(SearchValue searchValue);

}
