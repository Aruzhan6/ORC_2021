package com.pixel.meirlen.orc;

import android.app.Application;

import com.pixel.meirlen.orc.di.components.AppComponent;
import com.pixel.meirlen.orc.di.components.BasketComponent;
import com.pixel.meirlen.orc.di.components.CategoryComponent;
import com.pixel.meirlen.orc.di.components.DaggerAppComponent;
import com.pixel.meirlen.orc.di.components.DetailComponent;
import com.pixel.meirlen.orc.di.components.DiscountComponent;
import com.pixel.meirlen.orc.di.components.FieldComponent;
import com.pixel.meirlen.orc.di.components.HistoryComponent;
import com.pixel.meirlen.orc.di.components.MainComponent;
import com.pixel.meirlen.orc.di.components.OrderComponent;
import com.pixel.meirlen.orc.di.components.ProductComponent;
import com.pixel.meirlen.orc.di.components.QRComponent;
import com.pixel.meirlen.orc.di.components.SignUpComponent;
import com.pixel.meirlen.orc.di.modules.AppModule;
import com.pixel.meirlen.orc.helper.Constans;
import com.pixel.meirlen.orc.helper.ImageLoader;


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
    private HistoryComponent historyComponent;
    private DetailComponent detailComponent;
    private DiscountComponent discountComponent;
    private MainComponent mainComponent;
    private QRComponent qRrComponent;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(instance, Constans.BASE_URL))
                .build();

        ImageLoader.initInstance();

        com.onesignal.OneSignal.startInit(this)
                .inFocusDisplaying(com.onesignal.OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

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

    public HistoryComponent createHistoryComponent() {
        if (historyComponent == null) {
            historyComponent = appComponent.historyComponentBuilder().build();
        }
        return historyComponent;

    }

    public DiscountComponent createDiscountComponent() {
        if (discountComponent == null) {
            discountComponent = appComponent.discountComponentBuilder().build();
        }
        return discountComponent;
    }
    public DetailComponent createDetailComponent() {
        if (detailComponent == null) {
            detailComponent = appComponent.detailComponentBuilder().build();
        }
        return detailComponent;
    }

    public MainComponent createMainComponent() {
        if (mainComponent == null) {
            mainComponent = appComponent.mainComponentBuilder().build();
        }
        return mainComponent;
    }

    public QRComponent createQrComponent() {
        if (qRrComponent == null) {
            qRrComponent = appComponent.qrComponentBuilder().build();
        }
        return qRrComponent;

    }
}
