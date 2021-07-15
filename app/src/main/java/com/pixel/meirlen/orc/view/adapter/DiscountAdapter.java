package com.pixel.meirlen.orc.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pixel.meirlen.orc.R;
import com.pixel.meirlen.orc.helper.Constans;
import com.pixel.meirlen.orc.helper.ImageLoader;
import com.pixel.meirlen.orc.interfaces.ItemClickListener;
import com.pixel.meirlen.orc.model.Product;
import com.pixel.meirlen.orc.model.discount.Discount;
import com.pixel.meirlen.orc.view.activity.DetailActivity;
import com.pixel.meirlen.orc.view.activity.ProductListActivity;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.pixel.meirlen.orc.view.activity.ProductListActivity.EXTRA_DISCOUNY_PRODUCTS;
import static com.pixel.meirlen.orc.view.activity.ProductListActivity.EXTRA_NAME_CATEGORY;


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
        final Discount discount = mList.get(position);
        if (discount != null) {
            switch (discount.getDiscountType()) {
                case TYPE_SALE:
                    ImageLoader.getInstance().load(c, Constans.BASE_IMAGE_URL+discount.getDiscountImage(), ((FirstHolder) holder).imageView);
                    break;
                case TYPE_POPULAR:
                    ImageLoader.getInstance().load(c, "http://kanzler-style.ru/upload/resize_cache/iblock/429/270_500_1/4291076be668eb7d8d2659942f1fe5df.jpg", ((FirstHolder) holder).imageView);
                    break;
            }

            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ((FirstHolder) holder).linearLayoutItems.removeAllViews();
            for (int i = 0; i < discount.getRandom_products().size(); i++) {
                assert inflater != null;
                View child = inflater.inflate(R.layout.product_disc_item, null);
                Product product = discount.getRandom_products().get(i);
                ImageView imageView = child.findViewById(R.id.product_img);
                TextView listText = child.findViewById(R.id.product_name);
                TextView product_price = child.findViewById(R.id.product_price);
                product_price.setText(String.valueOf(product.getProductPrice()) + c.getString(R.string.tenge));
                listText.setText(product.getProductName());




                if (product.getImages() != null && product.getImages().size() > 0) {
                    ImageLoader.getInstance().load(c, Constans.BASE_IMAGE_URL + product.getImages().get(0).getImagePath(), imageView);
                }

                ((FirstHolder) holder).linearLayoutItems.addView(child);
                child.setOnClickListener(v -> {
                    Intent intent = DetailActivity.detailProductIntent(c, product);
                    c.startActivity(intent);

                });

            }

            ((FirstHolder) holder).txtTitle.setText(discount.getDiscountName());
            ((FirstHolder) holder).setItemClickListener(pos -> {
                if (discount.getRandom_products() != null && discount.getRandom_products().size() > 0) {
                    Gson gson = new Gson();
                    String myJson = gson.toJson(discount);
                    Intent intent = new Intent(c, ProductListActivity.class);
                    intent.putExtra(EXTRA_DISCOUNY_PRODUCTS, myJson);
                    intent.putExtra(EXTRA_NAME_CATEGORY, discount.getDiscountName());

                    c.startActivity(intent);
                } else {
                    Toast.makeText(c, R.string.empty_cat, Toast.LENGTH_SHORT).show();
                }
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
        RoundedImageView imageView;
        @BindView(R.id.txtTitle)
        TextView txtTitle;
        ItemClickListener itemClickListener;

        @BindView(R.id.linearLayoutItems)
        LinearLayout linearLayoutItems;

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




