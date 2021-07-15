package com.pixel.meirlen.orc.helper;

import android.util.Log;

import com.pixel.meirlen.orc.model.filter.BooleanType;
import com.pixel.meirlen.orc.model.filter.MultipleSelect;
import com.pixel.meirlen.orc.model.filter.range.RangeInt;
import com.pixel.meirlen.orc.model.filter.StringType;
import com.pixel.meirlen.orc.model.request.Filter;
import com.google.gson.Gson;

public class FilterFactory {

    public static Filter createFilter(String key) {
        return init(key);
    }

    public static Filter createFilter() {
        return init(null);
    }

    private static Filter init(String key) {
        Filter filter = new Filter();
        BooleanType booleanType = new BooleanType();
        MultipleSelect multipleSelect = new MultipleSelect();
        RangeInt rangeInt = new RangeInt();
        StringType stringType = new StringType();
        filter.setMultipleSelect(multipleSelect);
        filter.setRangeInt(rangeInt);
        filter.set_boolean(booleanType);
        filter.setString(stringType);
        if (key != null) {
            filter.setName(key);
        }

        Gson gson = new Gson();
        String modelClass = gson.toJson(filter);
        Log.d("jsonFilter", modelClass);


        return filter;
    }

}
