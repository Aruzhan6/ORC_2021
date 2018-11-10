package com.example.meirlen.orc.presenter;


import com.example.meirlen.orc.base.BasePresenter;
import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.model.Field;
import com.example.meirlen.orc.model.SearchValue;
import com.example.meirlen.orc.view.CategoryView;
import com.example.meirlen.orc.view.FieldView;

import java.util.List;

public interface FieldPresenter extends BasePresenter<FieldView> {

    void getFields(String id);
    void getFieldValues(String id);
    void updateSearchValue(SearchValue searchValue);

}
