package com.pixel.meirlen.orc.view;



import com.pixel.meirlen.orc.model.qr.QR;

import java.util.List;


public interface QrView extends LoadingView {

    void getList(List<QR> products);


}
