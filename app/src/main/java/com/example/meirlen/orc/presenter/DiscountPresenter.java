package com.example.meirlen.orc.presenter;


import com.example.meirlen.orc.base.BasePresenter;
import com.example.meirlen.orc.model.discount.Discount;
import com.example.meirlen.orc.view.DiscountView;
import com.example.meirlen.orc.view.OrderView;

public interface DiscountPresenter extends BasePresenter<DiscountView> {

    void getList();


}
