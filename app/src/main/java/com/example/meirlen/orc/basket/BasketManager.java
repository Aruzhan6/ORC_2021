package com.example.meirlen.orc.basket;

// Когда происходит изменения в корзине. Оповещает всех подписчиков  реализующее интерфейс Observable .


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class BasketManager extends Observable implements Publisher {

    private List<Observer> subscribers = new ArrayList<>();

    public void update() {
        for (Observer outlet : this.subscribers) {
            outlet.update(this, "update");
        }
    }

    public void register(Observer outlet) {
        subscribers.add(outlet);
    }

}