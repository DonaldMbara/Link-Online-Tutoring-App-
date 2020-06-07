package com.example.link_online_tutoring_app_;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CalendarActvity extends AppCompatActivity {
    CalendarView calendarView;
    public static String theDay;
    public static String theMonth;
    public static String theYear;
    String hourOfEvent;
    String minutesOfEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        final AlertDialog alertDialog;

        calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                startActivity(new Intent(CalendarActvity.this, EventActivity.class));
                theDay = String.valueOf(dayOfMonth);
                theMonth = String.valueOf(month);
                theYear = String.valueOf(year);


            }
        });
    }

    @Override
    public void onBackPressed(){
        finish();
        super.onBackPressed();
        CalendarActvity.this.startActivity(new Intent(CalendarActvity.this, HomeActivity.class));
        finish();
    }

}
