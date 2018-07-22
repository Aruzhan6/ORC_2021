package com.example.meirlen.orc.view.adapter;

import android.content.Context;
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
import com.example.meirlen.orc.interfaces.OnAddCardListener;
import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.model.basket.Basket;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {

    private static final String TAG = "BasketAdapter";


    private List<Basket> mList;

    OnAddCardListener listener;
    private Context context;
    private ViewHolder viewHolder;

    public BasketAdapter(OnAddCardListener listener, List<Basket> mList, Context context) {
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
        Basket basket = mList.get(position);
        holder.listText.setText(basket.getProductName());
        holder.productPrice.setText(String.valueOf(basket.getTotalPrice()) + " " + context.getString(R.string.tenge));
        holder.textViewOptions.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(context, holder.textViewOptions);
            popup.inflate(R.menu.basket_popup_menu);
            popup.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.remove_item:
                        Toast.makeText(context, "Удален!", Toast.LENGTH_SHORT).show();

                        break;
                }
                return false;
            });
            popup.show();
        });


        holder.txtCount.setText(basket.getCartCount());
        Log.d(TAG, "onBindViewHolder: " + basket.getCartCount());
        if (basket.getCartCount() == null || basket.getCartCount().equals("0")) {
            holder.containerAdded.setVisibility(View.GONE);
            holder.productPrice.setVisibility(View.GONE);
            holder.productBtBuy.setVisibility(View.VISIBLE);
        } else {
            holder.containerAdded.setVisibility(View.VISIBLE);
            holder.productBtBuy.setVisibility(View.GONE);
            holder.productPrice.setVisibility(View.VISIBLE);
        }


        holder.productBtBuy.setOnClickListener(v -> {
            this.viewHolder = holder;
            holder.containerAdded.setVisibility(View.VISIBLE);
            holder.productBtBuy.setVisibility(View.GONE);
            listener.onAddCard(String.valueOf(basket.getProductId()), "0", position);
        });
        holder.icIncrease.setOnClickListener(v -> {
            this.viewHolder = holder;
            listener.onAddCard(String.valueOf(basket.getProductId()), "0", position);
        });
        holder.icDecrease.setOnClickListener(v -> {
            this.viewHolder = holder;
            listener.onAddCard(String.valueOf(basket.getProductId()), "1", position);
        });

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


        @BindView(R.id.product_bt_buy)
        Button productBtBuy;

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
