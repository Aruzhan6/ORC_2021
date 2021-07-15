package com.pixel.meirlen.orc.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity
public class Field {


    @PrimaryKey
    @SerializedName("field_id")
    @Expose
    private Integer fieldId;


    @SerializedName("field_category_id")
    @Expose
    private String fieldCategoryId;

    @SerializedName("field_type")
    @Expose
    private String fieldType;


    @SerializedName("field_name")
    @Expose
    private String fieldName;

    @Ignore
    @SerializedName("values")
    @Expose
    private List<SearchValue> values = null;

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldCategoryId() {
        return fieldCategoryId;
    }

    public void setFieldCategoryId(String fieldCategoryId) {
        this.fieldCategoryId = fieldCategoryId;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }


    public List<SearchValue> getValues() {
        return values;
    }

    public void setValues(List<SearchValue> values) {
        this.values = values;
    }
}