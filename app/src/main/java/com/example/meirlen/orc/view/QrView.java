package com.example.meirlen.orc.view;



import com.example.meirlen.orc.model.qr.QR;

import java.util.List;


public interface QrView extends LoadingView {

    void getList(List<QR> products);


}
