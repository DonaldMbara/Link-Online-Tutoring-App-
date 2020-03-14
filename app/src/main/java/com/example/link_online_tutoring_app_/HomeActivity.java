package com.example.link_online_tutoring_app_;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView v = findViewById(R.id.WelcomeHomeTv);
        final ListView list = findViewById(R.id.TheList);

        final BottomNavigationView bnv = findViewById(R.id.bottom_navigation);
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
            }
        });

    }
}
