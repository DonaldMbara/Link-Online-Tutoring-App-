package com.example.link_online_tutoring_app_.AptListUser;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.link_online_tutoring_app_.Adapter;
import com.example.link_online_tutoring_app_.Answer_Model;
import com.example.link_online_tutoring_app_.FacultiesAcitvities.CommerceActivity;
import com.example.link_online_tutoring_app_.HomeActivity;
import com.example.link_online_tutoring_app_.PostsActivity;
import com.example.link_online_tutoring_app_.R;
import com.example.link_online_tutoring_app_.ScheduleActivity;
import com.example.link_online_tutoring_app_.ViewAnswers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import managers.AsyncHTTP;

public class Apt_ListUsers extends AppCompatActivity{
    RecyclerView rv;
    ListUsers_Adapter lsa;
    String name;
    ArrayList<Name_Model> models = new ArrayList<>();
    Button Events_btn;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.apt_layout);
        Events_btn = findViewById(R.id.schedule_btn);

//        lsa = new ListUsers_Adapter(this, getTheList());
        ContentValues cv = new ContentValues();
        getTheList(cv);
        rv.setAdapter(lsa);

    }

    private void getTheList(ContentValues cv){
        rv = findViewById(R.id.RVA);
        rv.setLayoutManager(new LinearLayoutManager(this));

        new AsyncHTTP("https://lamp.ms.wits.ac.za/~s1819369/getallnames.php", cv) {

            @Override
            protected void onPreExecute() {

            }

            @Override
            protected void onPostExecute(String output) {


                try {
                    JSONArray arr = new JSONArray(output);
                    ArrayList<Name_Model> mds = new ArrayList<>();
                    Name_Model md;
                    for (int i = 0; i < arr.length(); i++) {
                        final JSONObject ob = (JSONObject) arr.get(i);
                        md = new Name_Model();

                        name = ob.getString("Username");

                        md.setName(name);
                        mds.add(md);
                        models= mds;
                    }

                    if(models.size()==0){
                        Toast toast = Toast.makeText(Apt_ListUsers.this, "No users available on the platform", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START, 240, 0);
                        toast.show();

                    }
                    else {

                        lsa = new ListUsers_Adapter(Apt_ListUsers.this, models);
                        rv.setAdapter(lsa);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }




        }.execute();

    }

    @Override
    public void onBackPressed(){
        finish();
        super.onBackPressed();
        Apt_ListUsers.this.startActivity(new Intent(Apt_ListUsers.this, HomeActivity.class));
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    public void OnCLickEvents(View view){
        Intent it = new Intent();
        Apt_ListUsers.this.startActivity(new Intent(Apt_ListUsers.this, ScheduleActivity.class));
        finish();
    }

}
