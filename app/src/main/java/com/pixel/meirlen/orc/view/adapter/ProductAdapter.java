package com.pixel.meirlen.orc.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pixel.meirlen.orc.R;
import com.pixel.meirlen.orc.helper.Constans;
import com.pixel.meirlen.orc.helper.ImageLoader;
import com.pixel.meirlen.orc.interfaces.FavouriteMethodCaller;
import com.pixel.meirlen.orc.interfaces.ItemClickListener;
import com.pixel.meirlen.orc.interfaces.OnAddCardListener;
import com.pixel.meirlen.orc.model.Product;
import com.pixel.meirlen.orc.view.activity.DetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.pixel.meirlen.orc.helper.Constans.FAV_CHANGE;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private static final String TAG = "ProductAdapter";
    private ItemClickListener itemClickListener;


    private List<Product> mList;

    OnAddCardListener listener;
    private Context context;
    private ViewHolder viewHolder;

    FavouriteMethodCaller favMarkCallback;


    public ProductAdapter(ItemClickListener itemClickListener, OnAddCardListener listener, List<Product> mList, Context context, FavouriteMethodCaller favMarkCallback) {
        this.mList = mList;
        this.context = context;
        this.listener = listener;
        this.favMarkCallback = favMarkCallback;
        this.itemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = mList.get(position);
        holder.listText.setText(product.getProductName());
        holder.txtCount.setText(product.getCartCount());
        holder.product_desc.setText(product.getProductWeight() + " гр");
        holder.product_price.setText(context.getString(R.string.tenge) + String.valueOf(product.getProductPrice()));
        Log.d(TAG, "onBindViewHolder: " + product.getCartCount());

        if (product.getDiscount() != null) {
            holder.discountTxt.setVisibility(View.VISIBLE);
            holder.product_price_discount.setVisibility(View.VISIBLE);
            int res = product.getProductPrice() - (product.getProductPrice() * product.getDiscount().getDiscountPercent() / 100);
            holder.product_price.setText(context.getString(R.string.tenge) + String.valueOf(res));
            holder.product_price_discount.setText(context.getString(R.string.tenge) + String.valueOf(product.getProductPrice()));
            holder.product_price_discount.setPaintFlags(holder.product_price_discount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.discountTxt.setText(String.valueOf(product.getDiscount().getDiscountPercent()) + "%");
        } else {

            holder.discountTxt.setVisibility(View.GONE);
            holder.product_price_discount.setVisibility(View.GONE);


        }

        if (product.getCartCount() == null || product.getCartCount().equals("0")) {
            holder.containerAdded.setVisibility(View.GONE);
            holder.productBtBuy.setVisibility(View.VISIBLE);
        } else {
            holder.containerAdded.setVisibility(View.VISIBLE);
            holder.productBtBuy.setVisibility(View.GONE);
        }
        if (product.getImages() != null && product.getImages().size() > 0) {
            ImageLoader.getInstance().load(context, Constans.BASE_IMAGE_URL + product.getImages().get(0).getImagePath(), holder.productImg);
        }

        holder.productBtBuy.setOnClickListener(v -> {
            this.viewHolder = holder;
            holder.containerAdded.setVisibility(View.VISIBLE);
            holder.productBtBuy.setVisibility(View.GONE);
            listener.onAddCard(String.valueOf(product.getProductId()), "0", position);
        });
        holder.icIncrease.setOnClickListener(v -> {
            this.viewHolder = holder;
            listener.onAddCard(String.valueOf(product.getProductId()), "0", position);
        });
        holder.icDecrease.setOnClickListener(v -> {
            this.viewHolder = holder;
            listener.onAddCard(String.valueOf(product.getProductId()), "1", position);
        });

        if (product.getFavourite())
            holder.imgViewFav.setImageDrawable(
                    context.getDrawable(R.drawable.ic_like));
        else
            holder.imgViewFav.setImageDrawable(
                    context.getDrawable(R.drawable.ic_dislike));

        holder.imgViewFav.setOnClickListener(view -> {
            this.viewHolder = holder;
            if (product.getFavourite()) {
                holder.imgViewFav.setImageDrawable(context.getDrawable(R.drawable.ic_dislike));
                favMarkCallback.markSpaceFavourie(product.getProductId());
                product.setFavourite(false);
            } else {
                holder.imgViewFav.setImageDrawable(context.getDrawable(R.drawable.ic_like));
                favMarkCallback.markSpaceFavourie(product.getProductId());
                product.setFavourite(true);

            }
        });
        holder.productImg.setOnClickListener(v -> {
            itemClickListener.onItemClick(position);
        });
        holder.product_img_container.setOnClickListener(v -> {
            itemClickListener.onItemClick(position);
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void hideLoading(int requestCode) {
        if (requestCode == FAV_CHANGE) {
            viewHolder.progressBarFav.setVisibility(View.GONE);
            viewHolder.imgViewFav.setVisibility(View.VISIBLE);
        } else {
            viewHolder.progressBar.setVisibility(View.GONE);
            viewHolder.txtCount.setVisibility(View.VISIBLE);
        }
    }

    public void showLoading(int requestCode) {
        if (requestCode == FAV_CHANGE) {
            viewHolder.progressBarFav.setVisibility(View.VISIBLE);
            viewHolder.imgViewFav.setVisibility(View.GONE);
        } else {
            viewHolder.progressBar.setVisibility(View.VISIBLE);
            viewHolder.txtCount.setVisibility(View.GONE);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.product_name)
        TextView listText;

        @BindView(R.id.product_bt_buy)
        LinearLayout productBtBuy;

        @BindView(R.id.container_added)
        LinearLayout containerAdded;

        @BindView(R.id.txt_count)
        TextView txtCount;

        @BindView(R.id.product_price)
        TextView product_price;
        @BindView(R.id.product_price_discount)
        TextView product_price_discount;
        @BindView(R.id.product_img_container)
        RelativeLayout product_img_container;
        @BindView(R.id.product_desc)
        TextView product_desc;

        @BindView(R.id.ic_decrease)
        ImageView icDecrease;

        @BindView(R.id.ic_increase)
        ImageView icIncrease;

        @BindView(R.id.imgViewFav)
        ImageView imgViewFav;

        @BindView(R.id.progressBar)
        ProgressBar progressBar;


        @BindView(R.id.progressBarFav)
        ProgressBar progressBarFav;

        @BindView(R.id.product_img)
        ImageView productImg;

        //
        @BindView(R.id.discountTxt)
        TextView discountTxt;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {


        }


    }
}
