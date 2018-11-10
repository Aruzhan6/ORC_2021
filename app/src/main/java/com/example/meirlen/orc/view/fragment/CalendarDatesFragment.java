package com.example.meirlen.orc.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.calendarmeirlen.CalendarViewInteractor;
import com.example.calendarmeirlen.components.CustomizableCalendar;
import com.example.calendarmeirlen.interactors.AUCalendar;
import com.example.calendarmeirlen.interfaces.GlobalVariables;
import com.example.calendarmeirlen.interfaces.IShowTimePicker;
import com.example.calendarmeirlen.model.MyCalendar;
import com.example.meirlen.orc.R;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;


public class CalendarDatesFragment extends Fragment implements IShowTimePicker {

    private OnFragmentInteractionListener mListener;

    private Bundle bundle;

    CustomizableCalendar customizableCalendar;
    @BindView(R.id.createButton)
    Button createButton;
    @BindView(R.id.infoText)
    TextView infoText;
    private CompositeDisposable subscriptions;


    private int stateTimePicker = 0;

    public CalendarDatesFragment() {
        // Required empty public constructor
    }

    public static CalendarDatesFragment newInstance() {
        CalendarDatesFragment fragment = new CalendarDatesFragment();
        return fragment;
    }

    public static CalendarDatesFragment newInstance(String param1, String param2) {
        CalendarDatesFragment fragment = new CalendarDatesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this
        View rootView = inflater.inflate(R.layout.fragment_calendar_dates, container, false);
        ButterKnife.bind(this, rootView);





        GlobalVariables.iShowTimePicker = this;
        customizableCalendar = (CustomizableCalendar) rootView.findViewById(com.example.calendarmeirlen.R.id.view_month);


        updateData();


        if (getActivity().getIntent().getBooleanExtra("create_button", false)) {
            createButton.setVisibility(View.VISIBLE);
        }

        bundle = this.getArguments();
        if (bundle != null) {
            if (bundle.getString("type") != null) {

                if (bundle.getString("type").equals("3")) {
                    GlobalVariables.type_calendar = 3;
                    infoText.setText("Нажмите на день заезда в календаре, затем на день выезда. ");

                }
            }
        }


        return rootView;
    }


    @OnClick(R.id.createButton)
    public void setCreateButton() {
        if (check()) {
            getActivity().setResult(Activity.RESULT_OK);
            getActivity().finish();
        }
    }


    public boolean check() {
        int selectedPeriod = Integer.parseInt(getActivity().getIntent().getStringExtra("type_date"));

        if (selectedPeriod == 0) {
            if (GlobalVariables.start_date == null) {
                Toast.makeText(getContext(), "Выберите дату начала", Toast.LENGTH_SHORT).show();
                return false;

            } else {

                if (GlobalVariables.start_time == null) {
                    Toast.makeText(getContext(), "Выберите время", Toast.LENGTH_SHORT).show();
                    return false;


                } else {
                    if (GlobalVariables.min_hour_selectable) {


                    } else {
                        Toast.makeText(getContext(), "Минимальная количество часов ", Toast.LENGTH_SHORT).show();
                        return false;
                    }


                }
            }
        } else {

            if (GlobalVariables.start_date == null) {
                Toast.makeText(getContext(), "Выберите дату заезда", Toast.LENGTH_SHORT).show();
                return false;

            } else {


            }
        }

        return true;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Toast.makeText(getContext(), "Clear", Toast.LENGTH_SHORT).show();
        GlobalVariables.start_date = null;
        GlobalVariables.end_date = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void updateData() {

        subscriptions = new CompositeDisposable();
        // setting up first and last month that must be showed in the calendar
        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        DateTime firstMonth = new DateTime().withDayOfMonth(1).plusDays(dayOfMonth);
        DateTime lastMonth = new DateTime().plusMonths(3).withDayOfMonth(1);

        final MyCalendar calendar = new MyCalendar(firstMonth, lastMonth);


        calendar.setMultipleSelection(true);


        if (GlobalVariables.bookings != null) {
            calendar.setBookings(GlobalVariables.bookings);
        }
        if (GlobalVariables.start_date != null) {
            calendar.setFirstSelectedDay(GlobalVariables.start_date);

        }
        if (GlobalVariables.end_date != null) {
            calendar.setLastSelectedDay(GlobalVariables.end_date);

        }


        //  Toast.makeText(getContext(), String.valueOf(GlobalVariables.start_date.getDayOfMonth())+"  //  "+String.valueOf(GlobalVariables.end_date.getDayOfMonth()), Toast.LENGTH_SHORT).show();
        final CalendarViewInteractor calendarViewInteractor = new CalendarViewInteractor(getContext());

        AUCalendar auCalendar = AUCalendar.getInstance(calendar);
        calendarViewInteractor.updateCalendar(calendar);
        subscriptions.add(
                auCalendar.observeChangesOnCalendar()
                        .subscribe(changeSet -> calendarViewInteractor.updateCalendar(calendar))
        );

        customizableCalendar.injectViewInteractor(calendarViewInteractor);

        ;
    }


    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }


    @Override
    public void onPause() {
        super.onPause();
        subscriptions.clear();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showTimePicker(int stateTimePicker) {
        this.stateTimePicker = stateTimePicker;

    }

}
