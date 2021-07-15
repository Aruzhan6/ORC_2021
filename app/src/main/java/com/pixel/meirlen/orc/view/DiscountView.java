package com.pixel.meirlen.orc.view;


import com.pixel.meirlen.orc.model.Product;
import com.pixel.meirlen.orc.model.discount.Discount;

import java.util.List;

public interface DiscountView extends LoadingView {

    void getList(List<Discount> products);


}
