package com.example.meirlen.orc.view;


import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.model.discount.Discount;

import java.util.List;

public interface DiscountView extends LoadingView {

    void getList(List<Discount> products);


}
