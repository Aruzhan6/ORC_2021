package com.example.calendarmeirlen.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.calendarmeirlen.R;
import com.example.calendarmeirlen.adapter.MonthAdapter;
import com.example.calendarmeirlen.interactors.AUCalendar;
import com.example.calendarmeirlen.interactors.ViewInteractor;
import com.example.calendarmeirlen.interfaces.GlobalVariables;
import com.example.calendarmeirlen.model.CalendarItem;
import com.example.calendarmeirlen.presenter.interfeaces.CustomizableCalendarPresenter;
import com.example.calendarmeirlen.view.BaseView;

import org.joda.time.DateTime;


public class MonthGridView extends LinearLayout implements BaseView {
    protected DateTime monthDateTime;
    private MonthAdapter calendarAdapter;
    private GridView calendarGrid;
    private DateTime currentMonth;
    private
    @LayoutRes
    int layoutResId = -1;


    Context context;

    private
    @LayoutRes
    int dayLayoutResId = -1;
    private ViewInteractor viewInteractor;

    public MonthGridView(Context context) {
        this(context, null);
        this.context = context;
        GlobalVariables.firstItearation = true;
    }

    public MonthGridView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        GlobalVariables.start_date_equals_current_date = false;

    }

    public MonthGridView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
        GlobalVariables.start_date_equals_current_date = false;

    }

    private void init(Context context, AttributeSet attrs) {
        monthDateTime = new DateTime();
        layoutResId = R.layout.calendar_view;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomizableCalendar);
        if (typedArray != null) {
            layoutResId = typedArray.getResourceId(R.styleable.CustomizableCalendar_month_layout, R.layout.calendar_view);
            dayLayoutResId = typedArray.getResourceId(R.styleable.CustomizableCalendar_cell_layout, R.layout.calendar_cell);
            typedArray.recycle();
        }

    }

    @Override
    public void setLayoutResId(@LayoutRes int layoutResId) {
        if (layoutResId != -1) {
            this.layoutResId = layoutResId;
        }
    }

    public void setDayLayoutResId(int dayLayoutResId) {
        if (layoutResId != -1) {
            this.dayLayoutResId = dayLayoutResId;
        }
    }

    @Override
    public void injectViewInteractor(ViewInteractor viewInteractor) {
        this.viewInteractor = viewInteractor;
        bindViews();
        setupCalendar();
    }

    @Override
    public void injectPresenter(CustomizableCalendarPresenter presenter) {

    }

    private void bindViews() {
        LinearLayout calendarLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(layoutResId, this);
        calendarGrid = (GridView) calendarLayout.findViewById(android.R.id.widget_frame);
    }

    private void setupCalendar() {
        if (currentMonth == null) {
            currentMonth = AUCalendar.getInstance().getCurrentMonth();
        }
        calendarAdapter = new MonthAdapter(getContext(), currentMonth);

        calendarAdapter.setLayoutResId(dayLayoutResId);

        calendarAdapter.injectViewInteractor(viewInteractor);

        calendarAdapter.refreshDays();

        calendarGrid.setAdapter(calendarAdapter);


        calendarGrid.setOnItemClickListener((parent, view, position, id) -> {
            Object currentObj = calendarAdapter.getItem(position);
            if (currentObj != null) {
                CalendarItem calendarItem = (CalendarItem) currentObj;


                //Day

                if (calendarItem.isSelectable()) {


                    if (GlobalVariables.start_date == null) {
                        compareTwoDate(calendarItem.getDateTime());

                    } else if (GlobalVariables.start_date.compareTo(calendarItem.getDateTime()) > 0) {
                        compareTwoDate(calendarItem.getDateTime());


                    } else if (GlobalVariables.end_date != null && !GlobalVariables.firstItearation) {
                        compareTwoDate(calendarItem.getDateTime());

                    } else {
                        if (calendarItem.getDateTime().getDayOfMonth() != GlobalVariables.start_date.getDayOfMonth())
                            GlobalVariables.end_date = calendarItem.getDateTime().withHourOfDay(12);
                        GlobalVariables.firstItearation = false;


                    }


                    calendarAdapter.setSelected(calendarItem.getDateTime());


                }

            }


        });

    }

    public boolean compareTwoDate(DateTime compareDateTime) {
        DateTime currentDateTime = new DateTime();
        if (currentDateTime.getDayOfMonth() == compareDateTime.getDayOfMonth() & currentDateTime.getMonthOfYear() == compareDateTime.getMonthOfYear()) {

            GlobalVariables.start_date = currentDateTime.plusHours(2);
            GlobalVariables.start_date_equals_current_date = true;
            return true;
        } else {
            GlobalVariables.start_date_equals_current_date = false;
            GlobalVariables.start_date = compareDateTime.withHourOfDay(14);

        }

        return false;
    }

    @Override
    public void refreshData() {
        setupCalendar();
    }

    public void subscribe() {
        if (calendarAdapter != null) {
            calendarAdapter.subscribe();
        }
    }

    public void unsubscribe() {
        if (calendarAdapter != null) {
            calendarAdapter.unsubscribe();
        }
    }

    public void setCurrentMonth(DateTime currentMonth) {
        this.currentMonth = currentMonth;
        setupCalendar();
    }
}