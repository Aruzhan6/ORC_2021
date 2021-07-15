package com.pixel.meirlen.orc.basket;

// Когда происходит изменения в корзине. Оповещает всех подписчиков  реализующее интерфейс Observable .


import android.util.Log;

import com.pixel.meirlen.orc.model.CardResponse;
import com.pixel.meirlen.orc.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BasketManager extends Observable implements Publisher {
    public static int COUNT_CART = 0;
    private static final String TAG = "BasketManager";


    @Inject
    public BasketManager() {

    }

    private List<Observer> subscribers = new ArrayList<>();

    public void update(Integer count) { // Count of all products
        COUNT_CART = count;
        for (Observer outlet : this.subscribers) {
            outlet.update(this, "update");
        }
    }

    public void update(CardResponse response, Product product) { //This method calls for item product
        Integer last_count;
        if (product.getCartCount() == null) {
            last_count = 0;
        } else if (response.getCartCount() == 0) {
            last_count = 1;
        } else {
            last_count = Integer.valueOf(product.getCartCount());
        }

        COUNT_CART = COUNT_CART - last_count;
        COUNT_CART = COUNT_CART + response.getCartCount();
        for (Observer outlet : this.subscribers) {
            outlet.update(this, "update");
        }
    }

    public void deleteItem(Integer count) { //This method calls for item product
        COUNT_CART = COUNT_CART - count;
        for (Observer outlet : this.subscribers) {
            outlet.update(this, "update");
        }
    }

    public void register(Observer outlet) {
        subscribers.add(outlet);
    }

    public void disconnect() {
        subscribers.clear();
        COUNT_CART = 0;
    }

}