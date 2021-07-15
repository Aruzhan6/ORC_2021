package com.pixel.meirlen.orc.presenter;


import com.pixel.meirlen.orc.base.BasePresenter;
import com.pixel.meirlen.orc.model.discount.Discount;
import com.pixel.meirlen.orc.view.DiscountView;
import com.pixel.meirlen.orc.view.OrderView;

public interface DiscountPresenter extends BasePresenter<DiscountView> {

    void getList();


}
