package com.example.link_online_tutoring_app_;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class EventActivity extends AppCompatActivity {
    String eventName;
    String hourOfEvent;
    String minutesOfEvent;
    Context mContent = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        final EditText EventName = findViewById(R.id.events_id);
        ImageButton SelectTime = findViewById(R.id.selectiveTime);
        Button AddEvent = findViewById(R.id.addEvent);
        Button ViewEvents = findViewById(R.id.viewEvent);

        final String eventName = EventName.getText().toString().trim();
        Calendar calendar = Calendar.getInstance();
        final int currHour = calendar.get(Calendar.HOUR_OF_DAY);
        final int currMinutes = calendar.get(Calendar.MINUTE);

        SelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(mContent, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hourOfEvent = String.valueOf(hourOfDay);
                        minutesOfEvent = String.valueOf(minute);
                    }
                },currHour,currMinutes,true);
                timePickerDialog.show();

            }
        });

        AddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(eventName)) {

                    EventName.setError("Enter Name Of Event");
                }
                else{

                }

            }
        });
        ViewEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public void onBackPressed(){
        finish();
        super.onBackPressed();
        EventActivity.this.startActivity(new Intent(EventActivity.this, CalendarActvity.class));
        finish();
    }
}
