package com.example.meirlen.orc.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.meirlen.orc.R;
import com.example.meirlen.orc.model.Product;


import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    public List<Product> mList;
    private Context context;

    public ProductAdapter(List<Product> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.listText.setText(mList.get(position).getProductName());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView listText;


        ViewHolder(View view) {
            super(view);
            listText = itemView.findViewById(R.id.product_name);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {





        }
    }
}
