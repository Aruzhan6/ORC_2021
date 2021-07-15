package com.pixel.meirlen.orc.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Category {


    @PrimaryKey
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;

    @SerializedName("category_name")
    @Expose
    private String categoryName;

    @SerializedName("category_icon")
    @Expose
    private String categoryIcon;

    @SerializedName("category_parent_id")
    @Expose
    private String categoryParentId;


    @Ignore
    @SerializedName("children")
    @Expose
    private List<Category> children = null;


    @Ignore
    @SerializedName("fields")
    @Expose
    private List<Field> fields = null;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }



    public String getCategoryParentId() {
        return categoryParentId;
    }

    public void setCategoryParentId(String categoryParentId) {
        this.categoryParentId = categoryParentId;
}

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }
}