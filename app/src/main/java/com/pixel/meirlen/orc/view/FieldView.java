package com.pixel.meirlen.orc.view;

import com.pixel.meirlen.orc.model.Field;
import com.pixel.meirlen.orc.model.Product;
import com.pixel.meirlen.orc.model.SearchValue;

import java.util.List;


public interface FieldView extends LoadingView {

    void getList(List<Field> fields);
    void getFieldValues(List<SearchValue> fields);

}
