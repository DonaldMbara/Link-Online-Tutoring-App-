package com.example.link_online_tutoring_app_;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CalendarActvity extends AppCompatActivity {
    CalendarView calendarView;
    String theDay;
    String theMonth;
    String theYear;
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
                startActivity(new Intent(CalendarActvity.this,EventActivity.class));
                /*theDay = String.valueOf(dayOfMonth);
                theMonth = String.valueOf(month);
                theYear = String.valueOf(year);
                AlertDialog.Builder builder = new AlertDialog.Builder(getBaseContext());
                builder.setCancelable(true);
                final View addView = LayoutInflater.from(getParent()).inflate(R.layout.activity_new_event,null);
                EditText EventName = addView.findViewById(R.id.events_id);
                TextView EventTime = addView.findViewById(R.id.eventTime);
                ImageButton SetTime = addView.findViewById(R.id.eventTime);
                Button AddEvent = addView.findViewById(R.id.addEvent);
                Button ViewEvent = addView.findViewById(R.id.viewEvent);
                SetTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int hours = 24;
                        int minutes = 60;
                        TimePickerDialog timePickerDialog = new TimePickerDialog(addView.getContext(), R.style.Theme_AppCompat_Dialog
                                , new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                  hourOfEvent = String.valueOf(hourOfDay);
                                  minutesOfEvent = String.valueOf(minute);

                            }
                        },hours,minutes,false);
                    }
                });*/


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
