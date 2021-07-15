package com.pixel.meirlen.orc.interfaces;


import com.pixel.meirlen.orc.model.Review;
import com.pixel.meirlen.orc.model.history.History;

public interface ItemCallback {
    void onItemClick(History history);

    void onAddReview(String review, String id, Review reviewOb);
}
