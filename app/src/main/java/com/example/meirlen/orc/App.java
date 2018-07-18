package com.example.meirlen.orc;

import android.app.Application;

import com.example.meirlen.orc.di.components.AppComponent;
import com.example.meirlen.orc.di.components.CategoryComponent;
import com.example.meirlen.orc.di.components.DaggerAppComponent;
import com.example.meirlen.orc.di.components.FieldComponent;
import com.example.meirlen.orc.di.components.ProductComponent;
import com.example.meirlen.orc.di.modules.AppModule;
import com.example.meirlen.orc.model.Field;
import com.example.meirlen.orc.model.Product;


public class App extends Application {

    private static final String TAG = "App";

    public static App instance;
    private AppComponent appComponent;
    private CategoryComponent categoryComponent;
    private ProductComponent productComponent;
    private FieldComponent fieldComponent;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(instance,"http://orc.workapp.kz"))
                .build();

    }

    public CategoryComponent createCategoryComponent() {
        if (categoryComponent == null) {
            categoryComponent = appComponent.categoryBuilder().build();
        }

        return categoryComponent;
    }
    public ProductComponent createProductComponent() {
        if (productComponent == null) {
            productComponent = appComponent.productBuilder().build();
        }

        return productComponent;
    }

    public FieldComponent createFieldComponent() {
        if (fieldComponent == null) {
            fieldComponent = appComponent.fieldBuilder().build();
        }

        return fieldComponent;
    }

}
