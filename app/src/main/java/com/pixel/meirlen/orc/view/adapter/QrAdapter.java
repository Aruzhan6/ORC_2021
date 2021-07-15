package com.pixel.meirlen.orc.view.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pixel.meirlen.orc.R;
import com.pixel.meirlen.orc.interfaces.ItemCallback;
import com.pixel.meirlen.orc.model.qr.QR;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class QrAdapter extends RecyclerView.Adapter<QrAdapter.ViewHolder> {

    public List<QR> mList;

    private Activity context;

    private static final String TAG = "HistoryAdapter";
    private SparseArray<Boolean> expandMap = new SparseArray<>();
    private ItemCallback itemCallback;


    public QrAdapter(List<QR> mList, Activity context) {
        this.mList = mList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.producer_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        QR qr= mList.get(position);
        holder.title.setText(qr.getProducer().getProducerName());



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.textView57)
        TextView title;



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
