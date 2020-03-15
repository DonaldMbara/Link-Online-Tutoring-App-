package com.example.link_online_tutoring_app_;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.link_online_tutoring_app_.FacultiesAcitvities.CommerceActivity;
import com.example.link_online_tutoring_app_.FacultiesAcitvities.HealthScienceActivity;
import com.example.link_online_tutoring_app_.FacultiesAcitvities.HumanitiesActivity;
import com.example.link_online_tutoring_app_.FacultiesAcitvities.ScienceActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView v = findViewById(R.id.WelcomeHomeTv);
        final ListView list = findViewById(R.id.TheList);





        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Science");
        arrayList.add("Commerce");
        arrayList.add("Humanities");
        arrayList.add("Health Sciences");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem=(String) list.getItemAtPosition(position);
                Toast.makeText(HomeActivity.this,clickedItem, Toast.LENGTH_LONG).show();

                //perform item selected in the list view
                if(position ==0){
                    startActivity(new Intent(HomeActivity.this, ScienceActivity.class));
                    finish();
                }
                if(position ==1){
                    startActivity(new Intent(HomeActivity.this, CommerceActivity.class));
                    finish();

                }
                if(position ==2){
                    startActivity(new Intent(HomeActivity.this, HumanitiesActivity.class));
                    finish();

                }
                if(position ==3){
                    startActivity(new Intent(HomeActivity.this, HealthScienceActivity.class));
                    finish();

                }
            }
        });

        //initialise And assign variable
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        //perform item selected
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.private_chat:
                        startActivity(new Intent(HomeActivity.this,ChatActivity.class));
                        break;

                    case R.id.action_faculties:
                        break;
                }


            }
        });
    }

}
