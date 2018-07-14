package com.example.meirlen.orc.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meirlen.orc.R;
import com.example.meirlen.orc.rest.model.Category;
import com.example.meirlen.orc.view.activity.ChildCategoryActivity;


import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    public List<Category> mList;
    private Context context;

    public CategoryAdapter(List<Category> mList, Context context) {
        this.mList = mList;
        this.context = context;
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


                Intent intent = new Intent(context, ChildCategoryActivity.class);
                intent.putExtra(ChildCategoryActivity.EXTRA_NAME_PARENT_CATEGORY, mList.get(getAdapterPosition()).getCategoryName());
                intent.putExtra(ChildCategoryActivity.EXTRA_ID_PARENT_CATEGORY, String.valueOf(mList.get(getAdapterPosition()).getCategoryId()));
                context.startActivity(intent);



        }
    }
}
