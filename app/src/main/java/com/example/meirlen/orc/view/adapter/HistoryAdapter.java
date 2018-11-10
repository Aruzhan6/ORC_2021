package com.example.meirlen.orc.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.custom_checkbox.SmoothCheckBox;
import com.example.meirlen.orc.R;
import com.example.meirlen.orc.helper.Constans;
import com.example.meirlen.orc.helper.DateManager;
import com.example.meirlen.orc.helper.ImageLoader;
import com.example.meirlen.orc.helper.StatusOrder;
import com.example.meirlen.orc.interfaces.ItemCallback;
import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.model.Review;
import com.example.meirlen.orc.model.history.History;
import com.example.meirlen.orc.view.activity.DetailActivity;
import com.github.chuross.library.ExpandableLayout;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    public List<History> mList;

    private Activity context;

    private static final String TAG = "HistoryAdapter";
    private SparseArray<Boolean> expandMap = new SparseArray<>();
    private ItemCallback itemCallback;

    public HistoryAdapter(List<History> mList, Activity context, ItemCallback itemCallback) {
        this.mList = mList;
        this.context = context;
        this.itemCallback = itemCallback;
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
        //Date
        holder.txtDayOfMonth.setText(DateManager.getDayOfMonth(history.getCreatedAt()));
        holder.txtMonthOfYear.setText(DateManager.getMonthOfYear(history.getCreatedAt()));

        holder.linearLayoutItems.removeAllViews();
        holder.linearLayoutItems2.removeAllViews();
        if (expandMap.indexOfKey(position) >= 0 && expandMap.get(position)) {
            holder.expandableLayout.expand();
        } else {
            holder.expandableLayout.collapse();
        }

        int total = 0;
        if (history.getPurchases().size() > 3) {
            holder.openPartTwo.setVisibility(View.VISIBLE);
        } else {
            holder.openPartTwo.setVisibility(View.GONE);
        }

        for (int i = 0; i < history.getPurchases().size(); i++) {
            if (history.getPurchases().get(i).getProduct().getProductPrice() != null)
                total = total + history.getPurchases().get(i).getProduct().getProductPrice() * Integer.valueOf(history.getPurchases().get(i).getPurchaseCount());
        }


        holder.txtPrice.setText(String.valueOf(total) + context.getString(R.string.tenge));
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        holder.linearLayoutItems.removeAllViews();
        holder.linearLayoutItems2.removeAllViews();
        holder.linearStatusItems.removeAllViews();
        for (int i = 0; i < history.getPurchases().size(); i++) {
            View child = inflater.inflate(R.layout.purchases_item, null);
            Product product = history.getPurchases().get(i).getProduct();


            ImageView imageViewMain = (ImageView) child.findViewById(R.id.imageViewMain);
            if (product.getImages().size() > 0 && product.getImages() != null) {
                ImageLoader.getInstance().load(context, Constans.BASE_IMAGE_URL + product.getImages().get(0).getImagePath(), imageViewMain);
            }

            TextView product_name = (TextView) child.findViewById(R.id.product_name);
            TextView txt_total_price = (TextView) child.findViewById(R.id.txt_total_price);
            TextView txt_total = (TextView) child.findViewById(R.id.textTotal);
            // TextView txtDate=(TextView)child.findViewById(R.id.textDate);
            product_name.setText(history.getPurchases().get(i).getProduct().getProductName());
            //    txtDescr.setText(history.getPurchases().get(i).getProduct().getProductDescription());
            //  txtDate.setText(history.getPurchases().get(i).getProduct().getD);
            txt_total_price.setText(String.valueOf(history.getPurchases().get(i).getProduct().getProductPrice() * Integer.valueOf(history.getPurchases().get(i).getPurchaseCount())) + context.getString(R.string.tenge));
            txt_total.setText(history.getPurchases().get(i).getPurchaseCount() + " штук");
            if (i <= 2) {
                holder.linearLayoutItems.addView(child);
            } else {
                holder.linearLayoutItems2.addView(child);
            }

            child.setOnClickListener(v -> {
                Intent intent = DetailActivity.detailProductIntent(context, product);
                context.startActivity(intent);
            });

        }
        // add status lines
        int status = Integer.valueOf(history.getGroupStatus());
        Log.d(TAG, "onBindViewHolder: " + status);
        if (status == 5) {
            holder.imageCircle.setImageResource(R.drawable.history_item_circle);
        } else {
            holder.imageCircle.setImageResource(R.drawable.history_item_circle_green);
        }


        for (int i = 0; i < StatusOrder.values().length; i++) {
            View child = inflater.inflate(R.layout.item_status_on, null);
            SmoothCheckBox checkBox = child.findViewById(R.id.scb1);
            TextView title = child.findViewById(R.id.textView);
            checkBox.setClickable(false);

            if ((i + 1) <= status) {
                checkBox.setChecked(true);
            } else {
                child.setAlpha((float) 0.6);
                checkBox.setChecked(false);
            }


            holder.linearStatusItems.addView(child);
            title.setText(StatusOrder.getStatus(i + 1));
        }


        holder.openPartTwo.setOnClickListener(v -> {
            if (holder.expandableLayout.isExpanded()) {
                holder.expandableLayout.collapse();
                expandMap.put(position, false);
                holder.open_offer_btn.animate().rotation(90).start();
            } else {
                holder.expandableLayout.expand();
                expandMap.put(position, true);
                holder.open_offer_btn.animate().rotation(270).start();

            }
        });


        if (history.getGroupStatus().equals("4")) {
            holder.reviewAdd.setVisibility(View.VISIBLE);
        }

        holder.addReview.setOnClickListener(v -> {

            if (holder.ratingBar.getRating() > 0) {
                if (history.getPurchases().size() > 0) {
                    Review review =new Review();
                    review.setComment("zxz");
                    review.setEstimation("4");
                    review.setMinus("екси");
                    review.setWorth("cxc");
                    itemCallback.onAddReview(String.valueOf(holder.ratingBar.getRating()), String.valueOf(history.getPurchases().get(0).getPurchaseId()),review);
                }
            } else {
                Toast.makeText(context, "Поставьте оценку", Toast.LENGTH_SHORT).show();
            }
        });
        holder.layout_left.setOnClickListener(v -> itemCallback.onItemClick(history));
        holder.btLeft.setOnClickListener(v -> itemCallback.onItemClick(history));


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        @BindView(R.id.linearLayoutItems)
        LinearLayout linearLayoutItems;


        @BindView(R.id.reviewAdd)
        LinearLayout reviewAdd;

        @BindView(R.id.linearStatusItems)
        LinearLayout linearStatusItems;

        @BindView(R.id.linearLayoutItems2)
        LinearLayout linearLayoutItems2;

        @BindView(R.id.layout_right)
        LinearLayout layout_right;

        @BindView(R.id.layout_left)
        LinearLayout layout_left;

        @BindView(R.id.priceTxt)
        TextView txtPrice;

        @BindView(R.id.txtDayOfMonth)
        TextView txtDayOfMonth;

        @BindView(R.id.txtMonthOfYear)
        TextView txtMonthOfYear;

        @BindView(R.id.layout_expandable)
        ExpandableLayout expandableLayout;
        @BindView(R.id.openPartTwo)
        LinearLayout openPartTwo;

        @BindView(R.id.open_offer_btn)
        ImageView open_offer_btn;


        @BindView(R.id.imageCircle)
        ImageView imageCircle;

        @BindView(R.id.btLeft)
        Button btLeft;

        @BindView(R.id.ratingBar)
        RatingBar ratingBar;


        @BindView(R.id.button2)
        Button addReview;

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
