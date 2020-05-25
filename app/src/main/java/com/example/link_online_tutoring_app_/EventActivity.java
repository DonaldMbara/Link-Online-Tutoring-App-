package com.example.link_online_tutoring_app_;

import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import managers.AsyncHTTP;

public class EventActivity extends AppCompatActivity {
    //String eventName;
    static String fetchName;
    static String theName;
    String hourOfEvent;
    String minutesOfEvent;
    Context mContext = this;
    ContentValues cv = new ContentValues();
    ContentValues cv1 = new ContentValues();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        //final EditText EventName = findViewById(R.id.events_id);
        ImageButton SelectTime = findViewById(R.id.selectiveTime);
        Button AddEvent = findViewById(R.id.addEvent);
        Button ViewEvents = findViewById(R.id.viewEvent);

        SharedPreferences Prefs = LoginActivity.context.getSharedPreferences(LoginActivity.SHARED_PREF_LOGIN, Context.MODE_PRIVATE);
        final String StudentNumber = (Prefs.getString("Key", ""));

        //final String[] eventName = {EventName.getText().toString().trim()};
        Calendar calendar = Calendar.getInstance();
        final int currHour = calendar.get(Calendar.HOUR_OF_DAY);
        final int currMinutes = calendar.get(Calendar.MINUTE);

        cv1.put("studentNo",StudentNumber);
        fetchName = Get_Username(cv1);

        SelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
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

                    //date being chosen for event


                        if(fetchName == null){
                            fetchName = Get_Username(cv1);
                        }
                        if(fetchName!=null) {
                            cv.put("day", CalendarActvity.theDay);
                            cv.put("month", CalendarActvity.theMonth);
                            cv.put("time", hourOfEvent + ":" + minutesOfEvent);
                            cv.put("year", CalendarActvity.theYear);

                            // problem child
                            cv.put("Firstname", fetchName);

                            //just the studentNumber

                            cv.put("studentNo", StudentNumber);
                            cv.put("aval", "0");

                            Log.d("check date", CalendarActvity.theDay + "/" + CalendarActvity.theMonth + "/" + CalendarActvity.theYear);
                            Log.d("check time", hourOfEvent + ":" + minutesOfEvent);
                            Log.d("check num", StudentNumber);
                            Log.d("check name", fetchName);
                        }

                        //As we send

                        Do_Event_Send(cv, mContext);





            }
        });
        ViewEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private static void Do_Event_Send(ContentValues cv,final Context act ) {


        new AsyncHTTP("https://lamp.ms.wits.ac.za/~s1819369/event.php", cv) {

            @Override
            protected void onPreExecute() {

            }

            @Override
            protected void onPostExecute(String output) {
                if (output != null && output.equals("Event Logged")) {
                    //Log.d("check","check");
                    Toast toast = Toast.makeText(act, "Event Logged", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START, 240, 0);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(act, "Failed!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START, 240, 0);
                    toast.show();

                }
            }
        }.execute();
    }

    public static String Get_Username(ContentValues cv3){
        String SomeString;
        new AsyncHTTP("https://lamp.ms.wits.ac.za/~s1819369/getName.php", cv3){

            @Override
            protected void onPreExecute() {

            }

            @Override
            protected void onPostExecute(String output) {
                try {
                    JSONArray arr = new JSONArray(output);
                    JSONObject op = (JSONObject) arr.get(0);
                    theName = op.getString("FirstName");
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }.execute();

        return theName;
    }


    @Override
    public void onBackPressed(){
        finish();
        super.onBackPressed();
        EventActivity.this.startActivity(new Intent(EventActivity.this, CalendarActvity.class));
        finish();
    }
}
