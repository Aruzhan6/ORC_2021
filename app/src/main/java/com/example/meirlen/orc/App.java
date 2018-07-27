package com.example.meirlen.orc;

import android.app.Application;

import com.example.meirlen.orc.di.components.AppComponent;
import com.example.meirlen.orc.di.components.BasketComponent;
import com.example.meirlen.orc.di.components.CategoryComponent;
import com.example.meirlen.orc.di.components.DaggerAppComponent;
import com.example.meirlen.orc.di.components.DiscountComponent;
import com.example.meirlen.orc.di.components.FieldComponent;
import com.example.meirlen.orc.di.components.OrderComponent;
import com.example.meirlen.orc.di.components.ProductComponent;
import com.example.meirlen.orc.di.components.SignUpComponent;
import com.example.meirlen.orc.di.modules.AppModule;
import com.example.meirlen.orc.helper.ImageLoader;


public class App extends Application {

    private static final String TAG = "App";

    public static App instance;
    private AppComponent appComponent;
    private CategoryComponent categoryComponent;
    private ProductComponent productComponent;
    private FieldComponent fieldComponent;
    private SignUpComponent signUpComponent;
    private BasketComponent basketComponent;
    private OrderComponent orderComponent;
    private DiscountComponent discountComponent;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(instance, "http://orc.workapp.kz"))
                .build();

        ImageLoader.initInstance();

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

    public SignUpComponent createSignUpComponent() {
        if (signUpComponent == null) {
            signUpComponent = appComponent.signUpComponentBuilder().build();
        }

        return signUpComponent;
    }

    public BasketComponent createBasketComponent() {
        if (basketComponent == null) {
            basketComponent = appComponent.basketComponentBuilder().build();
        }
        return basketComponent;
    }

    public OrderComponent createOrderComponent() {
        if (orderComponent == null) {
            orderComponent = appComponent.orderComponentBuilder().build();
        }
        return orderComponent;

    }

    public DiscountComponent createDiscountComponent() {
        if (discountComponent == null) {
            discountComponent = appComponent.discountComponentBuilder().build();
        }
        return discountComponent;
    }
}
