package com.example.meirlen.orc.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.meirlen.orc.R;
import com.example.meirlen.orc.model.history.History;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    public List<History> mList;

    private Context context;

    private static final String TAG = "HistoryAdapter";

    public HistoryAdapter(List<History> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        History history = mList.get(position);
        Log.d(TAG, "onBindViewHolder: "+history.getPurchases().size());


        holder.listText.setText(history.getCreatedAt());

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i = 0; i < history.getPurchases().size(); i++) {

            View child = inflater.inflate(R.layout.purchases_item, null);


            holder.linearLayoutItems.addView(child);
        }


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.listText)
        TextView listText;
        @BindView(R.id.linearLayoutItems)
        LinearLayout linearLayoutItems;

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
