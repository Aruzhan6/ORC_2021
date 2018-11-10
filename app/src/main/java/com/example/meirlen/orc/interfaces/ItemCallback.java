package com.example.meirlen.orc.interfaces;


import com.example.meirlen.orc.model.Review;
import com.example.meirlen.orc.model.history.History;

public interface ItemCallback {
    void onItemClick(History history);

    void onAddReview(String review, String id, Review reviewOb);
}
