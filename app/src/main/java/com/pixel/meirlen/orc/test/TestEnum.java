package com.pixel.meirlen.orc.test;

import com.pixel.meirlen.orc.helper.StatusOrder;

public class TestEnum {

    public static void main(String[] args) {

       // StatusOrder statusOrder = StatusOrder

        for (StatusOrder o : StatusOrder.values()) {

            System.out.println(o.getName());

        }

    }
}
