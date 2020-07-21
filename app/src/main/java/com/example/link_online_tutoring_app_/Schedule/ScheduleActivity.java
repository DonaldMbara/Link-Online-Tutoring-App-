package com.example.link_online_tutoring_app_.Schedule;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.link_online_tutoring_app_.AptListUser.Apt_ListUsers;
import com.example.link_online_tutoring_app_.HomeActivity;
import com.example.link_online_tutoring_app_.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import managers.AsyncHTTP;

public class ScheduleActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    EventAdapter eventAdapter;
    ArrayList<ScheduleModel> eventsList;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.schedule_activity);
        String firstName = getIntent().getExtras().getString("Name");
        ContentValues cv = new ContentValues();
        cv.put("fname" , firstName);
        Log.d("CallEvents", "Calling");
        getEvents(cv);
        recyclerView.setAdapter(eventAdapter);
    }

    private void getEvents(ContentValues cv){
        recyclerView = findViewById(R.id.scheduleRecyclerVeiw);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ScheduleModel scheduleModel;

        new AsyncHTTP("https://lamp.ms.wits.ac.za/~s1819369/getevent.php", cv){

            @Override
            protected void onPreExecute() {


            }

            @Override
            protected void onPostExecute(String output) {
                try{
                    Log.d("Intry", "In1");
                    JSONArray arr = new JSONArray(output);
                    Log.d("Intry", "In2");
                    ArrayList<ScheduleModel> dummyModel = new ArrayList<>();

                    ScheduleModel sm;
                    String availabilityOutput;
                    String timeOutput;
                    String dayOutput;
                    String monthOutput;
                    Log.d("Intry", "In3");

                    for (int i =0; i < arr.length(); i++){
                        Log.d("InLoop", "In");
                        sm = new ScheduleModel();

                        JSONObject object  =(JSONObject) arr.get(i);

                        availabilityOutput = object.getString("Availability");
                        timeOutput = object.getString("Time");
                        dayOutput = object.getString("Day");
                        monthOutput = object.getString("Month");

                        if(availabilityOutput.equals("1")){
                            availabilityOutput = "Available";
                        }
                        else{
                            availabilityOutput= "Not Available";
                        }

                        monthOutput = getMonth(monthOutput);

                        sm.setAvailability(availabilityOutput);
                        sm.setTime(timeOutput);
                        sm.setDay(dayOutput);
                        sm.setMonth(monthOutput);

                        dummyModel.add(sm);
                        eventsList = dummyModel;
                    }

                    if(eventsList.size() == 0){
                        Toast toast = Toast.makeText(ScheduleActivity.this, "No Events", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START, 240, 0);
                        toast.show();

                    }

                    else{
                        Log.d("ListExist", "Yes");
                        eventAdapter = new EventAdapter(ScheduleActivity.this, eventsList);
                        recyclerView.setAdapter(eventAdapter);

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }.execute();


    }

    private String getMonth(String monthNumber){
        monthNumber = Integer.parseInt(monthNumber) + 1 + "";
        if(monthNumber.equals("1")){
            return "January";
        }
        else if(monthNumber.equals("2")){
            return "February";
        }
        else if(monthNumber.equals("3")){
            return "March";
        }
        else if(monthNumber.equals("4")){
            return "April";
        }
        else if(monthNumber.equals("5")){
            return "May";
        }
        else if(monthNumber.equals("6")){
            return "June";
        }
        else if(monthNumber.equals("7")){
            return "July";
        }
        else if(monthNumber.equals("8")){
            return "August";
        }
        else if(monthNumber.equals("9")){
            return "September";
        }
        else if(monthNumber.equals("10")){
            return "October";
        }
        else if(monthNumber.equals("11")){
            return "November";
        }
        else if(monthNumber.equals("12")){
            return "December";
        }
        else if(monthNumber.equals("1")) {
            return "January";
        }
        else{
            return "Any Month";
        }
    }

    @Override
    public void onBackPressed(){
        finish();
        super.onBackPressed();
        ScheduleActivity.this.startActivity(new Intent(ScheduleActivity.this, Apt_ListUsers.class));
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
