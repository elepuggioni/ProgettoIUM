package com.example.progettoium;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment{
    private Calendar date;
    private Calendar min_date = getToday();
    private Calendar max_date = getMax();
    private int caso;
    private int[] inizio;
    public DatePicker datePicker;
    //creo il listener (vedi metodi interfaccia)
    private DatePickerFragmentListener listener;


    public Dialog onCreateDialog (Bundle savedInstanceState){
        super.onCreateDialog(savedInstanceState);//uso il metodo di Dialog

        //SE è null gli metto valori di default
        if(date==null){
            date = Calendar.getInstance();
            date.set(Calendar.YEAR, 1995);
            date.set(Calendar.MONTH, Calendar.JANUARY);
            date.set(Calendar.DAY_OF_MONTH, 1);
        }

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View vista;

        if (getCaso() == 0){    //Partenza
            vista = inflater.inflate(R.layout.departure_picker,null);
            datePicker = vista.findViewById(R.id.datePicker1);

        }else {     //Ritorno
            vista = inflater.inflate(R.layout.landing_picker,null);
            datePicker = vista.findViewById(R.id.datePicker2);
        }

        initDatePicker(getInizio());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(vista);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                date.set(Calendar.YEAR, datePicker.getYear());
                date.set(Calendar.MONTH, datePicker.getMonth());
                date.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());

                if(listener!=null){
                    listener.onDatePickerFragmentOkButton(DatePickerFragment.this, date);
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(listener!=null){
                            listener.onDatePickerFragmentCancelButton(DatePickerFragment.this);
                        }
                    }
                }
        );

        return builder.create();
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setOnDatePickerFragmentChanged(DatePickerFragmentListener l){
        this.listener = l;
    }

    public int getCaso() {
        return caso;
    }

    public void setCaso(int caso) {
        this.caso = caso;
    }

    public void initDatePicker(int[]i ){
        datePicker.updateDate(i[0], i[1], i[2]);
        datePicker.setMinDate(min_date.getTimeInMillis());
        datePicker.setMaxDate(max_date.getTimeInMillis());
    }

    public int[] getInizio() {
        return inizio;
    }

    public void setInizio(int[] inizio) {
        this.inizio = inizio;
    }

    public Calendar getMin_date() {
        return min_date;
    }

    public void setMin_date(Calendar min_date) {
        this.min_date = min_date;
    }

    public void setMax_date(Calendar max_date){
        this.max_date = max_date;
    }

    public interface DatePickerFragmentListener{
        public void onDatePickerFragmentOkButton( DialogFragment dialog, Calendar date );
        public void onDatePickerFragmentCancelButton( DialogFragment dialog );
    }

    public Calendar getToday() {
        Calendar calendar = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);

        cal.set(Calendar.MONTH, mm);
        cal.set(Calendar.DAY_OF_MONTH, dd);
        cal.set(Calendar.YEAR, yy);
        return cal;
    }
    public Calendar getMax() {
        Calendar calendar = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);

        cal.set(Calendar.MONTH, mm);
        cal.set(Calendar.DAY_OF_MONTH, dd);
        cal.set(Calendar.YEAR, yy+5);
        return cal;
    }

    public void resetMax(){
        this.max_date = getMax();
    }

    public void resetMin(){
        this.min_date = getToday();
    }
}