package com.example.meirlen.orc.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.meirlen.orc.R;
import com.example.meirlen.orc.helper.ImageLoader;
import com.example.meirlen.orc.interfaces.ItemClickListener;
import com.example.meirlen.orc.model.discount.Discount;
import com.example.meirlen.orc.view.activity.ProductListActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DiscountAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ArrayList<Discount> mList = new ArrayList<>();
    private static final int TYPE_SALE = 1;
    private static final int TYPE_POPULAR = 2;
    private Activity c;

    public DiscountAdapter(Activity c) {
        this.c = c;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_SALE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discount_item_one, parent, false);
                return new FirstHolder(view, c);
            case TYPE_POPULAR:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discount_item_two, parent, false);
                return new FirstHolder(view, c);
        }
        return null;
    }
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        final Discount object = mList.get(position);
        if (object != null) {
            switch (object.getDiscountType()) {
                case TYPE_SALE:
                    ImageLoader.getInstance().load(c, object.getDiscountImage(), ((FirstHolder) holder).imageView);
                    break;
                case TYPE_POPULAR:
                    ImageLoader.getInstance().load(c, "http://kanzler-style.ru/upload/resize_cache/iblock/429/270_500_1/4291076be668eb7d8d2659942f1fe5df.jpg", ((FirstHolder) holder).imageView);
                    break;
            }
            ((FirstHolder) holder).setItemClickListener(pos -> {
                Intent intent = new Intent(c, ProductListActivity.class);
                c.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getDiscountType();
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    public void addCakes(List<Discount> newses) {
        mList.addAll(newses);
        notifyDataSetChanged();
    }

    public static class FirstHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.imageView)
        ImageView imageView;
        ItemClickListener itemClickListener;

        FirstHolder(View itemView, Context c) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(this.getLayoutPosition());
        }


    }


}




