package com.example.calendarmeirlen.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.calendarmeirlen.R;


/**
 * Created by D on 07.07.2017.
 */

public class ViewHolderVisitData extends RecyclerView.ViewHolder {


    public TextView getTimeVisit() {
        return timeVisit;
    }

    public void setTimeVisit(TextView timeVisit) {
        this.timeVisit = timeVisit;
    }

    public TextView getTimeWork() {
        return timeWork;
    }

    public void setTimeWork(TextView timeWork) {
        this.timeWork = timeWork;
    }

    public CardView getCardView() {
        return cardView;
    }

    public void setCardView(CardView cardView) {
        this.cardView = cardView;
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }

    public void setLinearLayout(LinearLayout linearLayout) {
        this.linearLayout = linearLayout;
    }

    TextView timeVisit;
    TextView timeWork;
    TextView duration;
    CardView cardView;
    LinearLayout linearLayout;
    LinearLayout linearLayout2;
    LinearLayout linearLayout3;

    public TextView getDuration() {
        return duration;
    }

    public LinearLayout getLinearLayout3() {
        return linearLayout3;
    }

    public LinearLayout getLinearLayout2() {
        return linearLayout2;
    }

    public ViewHolderVisitData(final View v) {
        super(v);
        timeVisit = (TextView) v.findViewById(R.id.timeVisit);
        duration = (TextView) v.findViewById(R.id.duration);
        timeWork = (TextView) v.findViewById(R.id.timeWork);
        cardView = (CardView) v.findViewById(R.id.cardViewVisit);
        linearLayout = (LinearLayout) v.findViewById(R.id.chooseVisitLL);
        linearLayout2 = (LinearLayout) v.findViewById(R.id.line);
        linearLayout3 = (LinearLayout) v.findViewById(R.id.line2);

    }
}