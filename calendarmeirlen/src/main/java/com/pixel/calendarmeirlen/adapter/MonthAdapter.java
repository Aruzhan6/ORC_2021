package com.pixel.calendarmeirlen.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.pixel.calendarmeirlen.R;
import com.pixel.calendarmeirlen.interactors.AUCalendar;
import com.pixel.calendarmeirlen.interactors.ViewInteractor;
import com.pixel.calendarmeirlen.interfaces.GlobalVariables;
import com.pixel.calendarmeirlen.model.CalendarFields;
import com.pixel.calendarmeirlen.model.CalendarItem;
import com.pixel.calendarmeirlen.presenter.interfeaces.CustomizableCalendarPresenter;
import com.pixel.calendarmeirlen.utils.DateUtils;
import com.pixel.calendarmeirlen.view.MonthView;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;


public class MonthAdapter extends BaseAdapter implements MonthView {
    private Context context;
    private AUCalendar calendar;
    private int layoutResId;
    private List<CalendarItem> days;
    private ViewInteractor viewInteractor;

    private DateTime currentMonth;


    private DateTime firstSelectedDay;
    private DateTime lastSelectedDay;
    private DateTime activeDay;


    private DateTime firstSelectedDay2;
    private DateTime lastSelectedDay2;


    private boolean multipleSelection;
    private int firstDayOfWeek;
    private CompositeDisposable subscriptions;
    private boolean subscribed;

    public MonthAdapter(Context context, DateTime currentMonth) {
        this.context = context;
        this.subscriptions = new CompositeDisposable();
        this.calendar = AUCalendar.getInstance();
        this.layoutResId = R.layout.calendar_cell;
        this.currentMonth = currentMonth.withDayOfMonth(1).withMillisOfDay(0);
        initFromCalendar();
        subscribe();

    }

    private void initFromCalendar() {
        firstSelectedDay = calendar.getFirstSelectedDay();
        if (firstSelectedDay != null) {
            firstSelectedDay = firstSelectedDay.withMillisOfDay(0);
        }
        lastSelectedDay = calendar.getLastSelectedDay();
        if (lastSelectedDay != null) {
            lastSelectedDay = lastSelectedDay.withMillisOfDay(0);
        }

        activeDay = calendar.getActiveDay();
        if (activeDay != null) {
            activeDay = activeDay.withMillisOfDay(0);
        }

        multipleSelection = calendar.isMultipleSelectionEnabled();
        firstDayOfWeek = calendar.getFirstDayOfWeek();
    }

    @Override
    public int getCount() {
        return days.size();
    }

    @Override
    public Object getItem(int position) {
        return days.get(position);
    }

    @Override
    public long getItemId(int position) {
        final CalendarItem item = days.get(position);
        if (item != null) {
            return days.get(position).getId();
        }
        return -1;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final CalendarItem currentItem = days.get(position);

        Integer backgroundResource = null;
        int color = Color.rgb(0, 0, 0);


        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoutResId, null);
        }

        if (viewInteractor != null && viewInteractor.hasImplementedMonthCellBinding()) {
            view = viewInteractor.onMonthCellBindView(view, currentItem);


        } else {
            final TextView dayView = view.findViewById(android.R.id.title);
            final View background = view.findViewById(android.R.id.background);
            View startSelectionView = view.findViewById(android.R.id.startSelectingText);

            final View endSelectionView = view.findViewById(android.R.id.stopSelectingText);
            final View passiveDay = view.findViewById(R.id.passiveText);
            Color textColor;


            if (currentItem != null) {  //if day isn't empty
                Log.d("days:", currentItem.getDayString());
                startSelectionView.setVisibility(View.GONE);
                endSelectionView.setVisibility(View.GONE);

                //   Текущий день

                if (currentItem.compareTo(calendar.getFirstMonth().minusDays(1).withMillisOfDay(0)) == 0) {
                    currentItem.setSelectable(true);

                  //  Typeface face = Typeface.createFromAsset(context.getAssets(),
                   //         "fonts/Roboto-Bold.ttf");
                    //dayView.setTypeface(face);
                    dayView.setTextSize(16);
                    backgroundResource = R.drawable.current_day_circle;
                    dayView.setText(currentItem.getDayString());


                }

                //   Прошедшие дни
                if (currentItem.compareTo(calendar.getFirstMonth().minusDays(1).withMillisOfDay(0)) < 0) {
                    currentItem.setSelectable(false);

                    //backgroundResource = R.drawable.passive_day;
                    dayView.setText(currentItem.getDayString());
                    color = Color.rgb(196,196,196);
                    dayView.setTextColor(Color.BLACK);
                    //passiveDay.setVisibility(View.VISIBLE);
                } else {
                    currentItem.setSelectable(true);


                }



                //Логика по выборке
//EEEEEE






                        if (currentItem.compareTo(calendar.getFirstMonth().minusDays(1).withMillisOfDay(0)) <0) {     //   Прошедшие дни
                            currentItem.setSelectable(false);
                            background.setBackground(null);
                            dayView.setTextColor(Color.BLACK);
                            dayView.setText(currentItem.getDayString());
                            dayView.setAlpha(0.6f);
                        } else {
                            currentItem.setSelectable(true);
                            dayView.setAlpha(1f);


                            if (firstSelectedDay != null) { //Если выбран дата начала


                                int startSelectedCompared = currentItem.compareTo(firstSelectedDay); //Сравнимаем с текущец датой



                                if (!multipleSelection) {
                                    if (startSelectedCompared == 0) {
                                        backgroundResource = R.drawable.empty_circle;
                                    }
                                }

                                else if (startSelectedCompared == 0) {


                                    if (lastSelectedDay == null || lastSelectedDay.equals(currentItem.getDateTime())) {
                                        backgroundResource = R.drawable.circle_selected;
                                    } else {
                                        backgroundResource = R.drawable.left_rounded_rectangle;
                                        endSelectionView.setVisibility(View.VISIBLE);
                                        startSelectionView.setBackgroundColor(Color.rgb(82,99,255));
                                        endSelectionView.setBackgroundColor(Color.rgb(82,99,255));
                                    }
                                }
                                else if (startSelectedCompared > 0 && lastSelectedDay != null) {

                                    int endSelectedCompared = currentItem.compareTo(lastSelectedDay);
                                    if (endSelectedCompared == 0) {


                                        backgroundResource = R.drawable.right_rounded_rectangle;
                                        startSelectionView.setVisibility(View.VISIBLE);
                                        startSelectionView.setBackgroundColor(Color.rgb(82,99,255));
                                        endSelectionView.setBackgroundColor(Color.rgb(82,99,255));
                                    } else if (endSelectedCompared < 0) {

                                        if (currentItem.isMonth_booking_day()) {

                                        } else {
                                            backgroundResource = R.drawable.rectangle;
                                            startSelectionView.setBackgroundColor(Color.rgb(82,99,255));
                                            endSelectionView.setBackgroundColor(Color.rgb(82,99,255));
                                            startSelectionView.setVisibility(View.VISIBLE);
                                            endSelectionView.setVisibility(View.VISIBLE);
                                        }

                                    }
                                }


                            }


                        }








                //Текущий день




                if (backgroundResource != null) {
                    background.setBackgroundResource(backgroundResource);

                    if (multipleSelection) {
                        color = Color.WHITE;
                    }

                    if (currentItem.compareTo(calendar.getFirstMonth().minusDays(1).withMillisOfDay(0)) == 0) {

                        color = Color.rgb(250,60,60);
                    }
                    if (currentItem.isMonth_booking_hourly()) {
                        color = Color.rgb(0, 0, 0);
                    }
                } else {
                    background.setBackground(null);
                }

                dayView.setTextColor(color);
                dayView.setText(currentItem.getDayString());
                /* else if (startSelectedCompared2 > 0 && lastSelectedDay2 != null) {


                    int endSelectedCompared2 = currentItem.compareTo(lastSelectedDay2);
                    if (endSelectedCompared2 == 0) {
                        backgroundResource2 = R.drawable.right_rounded_rectangle;
                        startSelectionView.setVisibility(View.VISIBLE);
                    } else if (endSelectedCompared2 < 0) {
                        backgroundResource2 = R.drawable.rectangle;
                        startSelectionView.setVisibility(View.VISIBLE);
                        endSelectionView.setVisibility(View.VISIBLE);
                    }                */


            } else {

                background.setBackground(null);
                dayView.setText(null);
            }


        }

        return view;
    }

    @Override
    public void refreshData() {
        refreshDays();
    }

    @Override
    public void setLayoutResId(@LayoutRes int layoutResId) {
        if (layoutResId != -1) {
            this.layoutResId = layoutResId;
        }
    }

    @Override
    public void injectViewInteractor(ViewInteractor viewInteractor) {
        this.viewInteractor = viewInteractor;
    }

    @Override
    public void injectPresenter(CustomizableCalendarPresenter presenter) {
    }


    @Override
    public void setSelected(DateTime dateSelected) {


        if (GlobalVariables.oper_state == 2) {
            checkBookingDay(dateSelected);
            notifyFirstSelectionUpdated(dateSelected);
            notifyLastSelectionUpdated(null);
            notifyDataSetChanged();
            GlobalVariables.oper_state = 0;

        } else if (checkLastBookingDay(dateSelected)) {

            Toast.makeText(context, "Этот период занят", Toast.LENGTH_SHORT).show();
        } else {


            if (viewInteractor != null && viewInteractor.hasImplementedSelection()) {
                int itemSelected = viewInteractor.setSelected(multipleSelection, dateSelected);
                switch (itemSelected) {
                    case 0:
                        notifyFirstSelectionUpdated(dateSelected);
                        break;
                    case 1:
                        notifyLastSelectionUpdated(dateSelected);
                        break;
                    default:
                        return;
                }
            } else {
                if (!multipleSelection) {
                    notifyFirstSelectionUpdated(dateSelected);
                } else {
                    if (firstSelectedDay != null) {
                        int startSelectedCompared = dateSelected.compareTo(firstSelectedDay);
                        if (startSelectedCompared < 0) {
                            notifyFirstSelectionUpdated(dateSelected);
                            GlobalVariables.oper_state = 1;
                            GlobalVariables.oper_state_minus = false;
                        } else if (lastSelectedDay != null) {
                            int endSelectedCompared = dateSelected.compareTo(lastSelectedDay);
                            if ((startSelectedCompared >= 0 && endSelectedCompared < 0) || endSelectedCompared > 0) {
                                notifyLastSelectionUpdated(dateSelected);
                            }
                        } else {
                            notifyLastSelectionUpdated(dateSelected);
                        }

                    } else {
                        notifyFirstSelectionUpdated(dateSelected);
                    }
                }
            }
            notifyDataSetChanged();

        }


        if (GlobalVariables.oper_state_minus) {
            calendarCalculate(dateSelected);

        } else {
            GlobalVariables.oper_state_minus = true;
        }


        if (GlobalVariables.oper_state == 1) {

            if (firstSelectedDay != null) {

                if(GlobalVariables.start_date!=null){

                    GlobalVariables.start_date = firstSelectedDay.withHourOfDay(GlobalVariables.start_date.getHourOfDay());


                }
                else {
                    if(compareTwoDate(GlobalVariables.start_date)){
                        Log.d("kkkll","равно");
                        GlobalVariables.start_date = firstSelectedDay.withHourOfDay(14);

                    }
                    else {
                        GlobalVariables.start_date = firstSelectedDay.withHourOfDay(14);
                    }
                }

                checkBookingDay(dateSelected);

            }
            GlobalVariables.end_date=null;
        }
        if (GlobalVariables.oper_state == 2) {


            if (lastSelectedDay != null) {
                if(lastSelectedDay!=GlobalVariables.start_date){
                    if(GlobalVariables.end_date!=null){
                        GlobalVariables.end_date = lastSelectedDay.withHourOfDay(GlobalVariables.end_date.getHourOfDay());
                    }
                    else {
                        GlobalVariables.end_date = lastSelectedDay.withHourOfDay(12);;
                    }

                }



            }
        }


    }





    public void setSelected2(DateTime dateSelected) {


        if (viewInteractor != null && viewInteractor.hasImplementedSelection()) {
            int itemSelected = viewInteractor.setSelected(multipleSelection, dateSelected);
            switch (itemSelected) {
                case 0:
                    notifyFirstSelectionUpdated(dateSelected);
                    break;
                case 1:
                    notifyFirstSelectionUpdated(dateSelected);
                    break;
                default:
                    return;
            }
        } else {
            if (!multipleSelection) {
                notifyFirstSelectionUpdated(dateSelected);
            } else {
                if (firstSelectedDay != null) {
                    int startSelectedCompared = dateSelected.compareTo(firstSelectedDay);
                    if (startSelectedCompared < 0) {
                        notifyFirstSelectionUpdated(dateSelected);

                    } else if (lastSelectedDay != null) {
                        int endSelectedCompared = dateSelected.compareTo(lastSelectedDay);
                        if ((startSelectedCompared >= 0 && endSelectedCompared < 0) || endSelectedCompared > 0) {
                            notifyFirstSelectionUpdated(dateSelected);
                        }
                    } else {
                        notifyFirstSelectionUpdated(dateSelected);
                    }

                } else {
                    notifyFirstSelectionUpdated(dateSelected);
                }
            }
        }
        notifyDataSetChanged();

    }



    public boolean compareTwoDate(DateTime compareDateTime ) {
        DateTime currentDateTime = new DateTime();
        if (currentDateTime.getDayOfMonth()==compareDateTime.getDayOfMonth() & currentDateTime.getMonthOfYear()==compareDateTime.getMonthOfYear() )
            return  true;

        return false;
    }

    public void setClickDay(DateTime dateSelected) {

        notifyActiveDayUpdated(dateSelected);
        notifyDataSetChanged();


       // Toast.makeText(context, "fgf", Toast.LENGTH_SHORT).show();

    }

    private boolean checkLastBookingDay(DateTime dateSelected) {

        if (GlobalVariables.start_date_booking != null) {
            int compareTo = dateSelected.compareTo(GlobalVariables.start_date_booking); //Сравнимаем с текущец датой
            if (compareTo > 0) {
                Log.d("Connn", "Это дата:" + dateSelected.getDayOfMonth() + "не входит в этот диапазон:");
                return true;


            }


        }


        return false;


    }

    private void checkBookingDay(DateTime dateSelected) {

        if(GlobalVariables.bookings!=null)

        for (int i = 0; i < calendar.getBookings().size(); i++) {

            DateTime first1 = calendar.getBookings().get(i).getTimestampStart().withMillisOfDay(0);
            DateTime second2 = calendar.getBookings().get(i).getTimestampEnd().withMillisOfDay(0);

            int compareTo = dateSelected.compareTo(first1); //Сравнимаем с текущец датой
            if (compareTo < 0) {
                Log.d("Connn", "Это дата:" + dateSelected.getDayOfMonth() + " входит в этот диапазон:" + first1 + "  ----" + second2);
                GlobalVariables.start_date_booking = first1;

                break;

            }

        }

    }

    private void calendarCalculate(DateTime dateSelected) {

        GlobalVariables.oper_state++;
        Log.d("opert", "position" + String.valueOf(GlobalVariables.oper_state) + " " + dateSelected);
    }

    private void notifyFirstSelectionUpdated(DateTime startSelected) {
        GlobalVariables.start_date_booking = null;
        this.firstSelectedDay = startSelected;
        this.calendar.setFirstSelectedDay(startSelected);
    }

    private void notifyLastSelectionUpdated(DateTime endSelected) {


        this.lastSelectedDay = endSelected;
        this.calendar.setLastSelectedDay(endSelected);


    }
    private void notifyActiveDayUpdated(DateTime endSelected) {


        this.activeDay = endSelected;
        this.calendar.setActiveDay(endSelected);


    }
    @Override
    public final void refreshDays() {
        final int empties;
        final int year = currentMonth.getYear();
        final int month = currentMonth.getMonthOfYear();
        final int firstDayOfMonth = currentMonth.getDayOfWeek() + 1;
        final int lastDayOfMonth = DateUtils.getDaysInMonth(month - 1, year);
        List<CalendarItem> updatedDays = new ArrayList<>();

        if (viewInteractor != null && viewInteractor.hasImplementedDayCalculation()) {
            days = viewInteractor.calculateDays(year, month, firstDayOfMonth, lastDayOfMonth);
        } else {
            // default days calculation
            if (firstDayOfMonth == firstDayOfWeek) {
                empties = 0;
            } else if (firstDayOfMonth < firstDayOfWeek) {
                empties = Calendar.SATURDAY - (firstDayOfWeek - 1);
            } else {
                empties = firstDayOfMonth - firstDayOfWeek;
            }

            int totDays = lastDayOfMonth + empties;
            for (int day = 1, position = 1; position <= totDays; position++) {
                if (position > empties) {
                    updatedDays.add(new CalendarItem(day++, month, year));
                } else {
                    updatedDays.add(null);
                }
            }

        }
        if (!updatedDays.equals(days)) {
            days = updatedDays;
            notifyDataSetChanged();
        }
    }

    public void subscribe() {
        if (!subscribed) {
            subscriptions.add(
                    calendar.observeChangesOnCalendar()
                            .subscribe(changeSet -> {
                                if (changeSet.isFieldChanged(CalendarFields.FIRST_DAY_OF_WEEK)
                                        || changeSet.isFieldChanged(CalendarFields.FIRST_SELECTED_DAY)
                                        || changeSet.isFieldChanged(CalendarFields.LAST_SELECTED_DAY)) {
                                    initFromCalendar();
                                    refreshDays();
                                }
                            })
            );
            subscribed = true;
        }
    }

    @Override
    public void unsubscribe() {
        if (subscribed) {
            subscriptions.clear();
            subscribed = false;
        }
    }


}