package com.example.meirlen.orc.view.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meirlen.orc.R;
import com.example.meirlen.orc.helper.Constans;
import com.example.meirlen.orc.helper.ImageLoader;
import com.example.meirlen.orc.interfaces.OnAddCardListener;
import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.view.activity.DetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {

    private static final String TAG = "BasketAdapter";


    private List<Product> mList;

    private OnAddCardListener listener;
    private Activity context;
    private ViewHolder viewHolder;

    public BasketAdapter(OnAddCardListener listener, List<Product> mList, Activity context) {
        this.mList = mList;
        this.context = context;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.basket_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product Product = mList.get(position);
        holder.listText.setText(Product.getProductName());
        //holder.listText.setText(String.valueOf(Product.getCartId()));
        holder.productPrice.setText(String.valueOf(Product.getProductPrice()) + " " + context.getString(R.string.tenge));
        double priceTotal;
        priceTotal = Product.getTotal_price();
        holder.productPriceChange.setText(String.valueOf(priceTotal) + " " + context.getString(R.string.tenge));


        if (Product.getDiscount() != null) {
            holder.discountTxt.setVisibility(View.VISIBLE);
            holder.product_price_discount.setVisibility(View.VISIBLE);
            int res = Product.getProductPrice() - (Product.getProductPrice() * Product.getDiscount().getDiscountPercent() / 100);
            holder.productPrice.setText(context.getString(R.string.tenge) + String.valueOf(res));
            holder.product_price_discount.setText(context.getString(R.string.tenge) + String.valueOf(Product.getProductPrice()));
            holder.product_price_discount.setPaintFlags(holder.product_price_discount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.discountTxt.setText(String.valueOf(Product.getDiscount().getDiscountPercent()) + "%");

            priceTotal = Product.getTotal_price();
            holder.productPriceChange.setText(String.valueOf(priceTotal) + " " + context.getString(R.string.tenge));
        } else {

            holder.discountTxt.setVisibility(View.GONE);
            holder.product_price_discount.setVisibility(View.GONE);


        }

        double finalPriceTotal = priceTotal;
        holder.textViewOptions.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(context, holder.textViewOptions);
            popup.inflate(R.menu.basket_popup_menu);
            popup.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.remove_item:
                        listener.onDeleteCard(position, finalPriceTotal);
                        break;
                }
                return false;
            });
            popup.show();
        });


        holder.txtCount.setText(Product.getCartCount() + " шт.");
        Log.d(TAG, "onBindViewHolder: " + Product.getCartCount());


        holder.icIncrease.setOnClickListener(v -> {
            this.viewHolder = holder;
            listener.onAddCard(String.valueOf(Product.getProductId()), "0", position);
        });
        holder.icDecrease.setOnClickListener(v -> {
            this.viewHolder = holder;
            listener.onAddCard(String.valueOf(Product.getProductId()), "1", position);


        });
        holder.productImg.setOnClickListener(v -> {
            Intent intent = DetailActivity.detailProductIntent(context, Product);
            context.startActivity(intent);


        });
        if (Product.getImages() != null && Product.getImages().size() > 0) {
            ImageLoader.getInstance().load(context, Constans.BASE_IMAGE_URL + Product.getImages().get(0).getImagePath(), holder.productImg);
        }


        //  ImageLoader.getInstance().initAvatar(context,Product.getProduct();

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void hideLoading() {
        viewHolder.progressBar.setVisibility(View.GONE);
        viewHolder.txtCount.setVisibility(View.VISIBLE);
    }

    public void showLoading() {
        viewHolder.progressBar.setVisibility(View.VISIBLE);
        viewHolder.txtCount.setVisibility(View.GONE);

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.product_name)
        TextView listText;


        @BindView(R.id.container_count_buttons)
        LinearLayout containerAdded;

        @BindView(R.id.txt_count)
        TextView txtCount;

        @BindView(R.id.ic_decrease)
        ImageView icDecrease;

        @BindView(R.id.ic_increase)
        ImageView icIncrease;

        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        @BindView(R.id.textViewOptions)
        TextView textViewOptions;

        @BindView(R.id.product_price)
        TextView productPrice;

        @BindView(R.id.priceChange)
        TextView productPriceChange;
        @BindView(R.id.product_img)
        ImageView productImg;

        @BindView(R.id.discountTxt)
        TextView discountTxt;


        @BindView(R.id.product_price_discount)
        TextView product_price_discount;

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
