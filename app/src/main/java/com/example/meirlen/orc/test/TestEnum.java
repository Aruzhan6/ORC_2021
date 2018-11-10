package com.example.meirlen.orc.test;

import com.example.meirlen.orc.helper.StatusOrder;

public class TestEnum {

    public static void main(String[] args) {

       // StatusOrder statusOrder = StatusOrder

        for (StatusOrder o : StatusOrder.values()) {

            System.out.println(o.getName());

        }

    }
}
