package com.pixel.meirlen.orc.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pixel.meirlen.orc.R;
import com.pixel.meirlen.orc.interfaces.OnCategoryClickListener;
import com.pixel.meirlen.orc.model.Category;


import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    public List<Category> mList;
    private Context context;
    private OnCategoryClickListener categoryClickListener;

    public CategoryAdapter(OnCategoryClickListener categoryClickListener, List<Category> mList, Context context) {
        this.mList = mList;
        this.context = context;
        this.categoryClickListener = categoryClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.listText.setText(mList.get(position).getCategoryName());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView listText;


        ViewHolder(View view) {
            super(view);
            listText = itemView.findViewById(R.id.listText);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            categoryClickListener.onItemClick(getAdapterPosition());
        }
    }
}
