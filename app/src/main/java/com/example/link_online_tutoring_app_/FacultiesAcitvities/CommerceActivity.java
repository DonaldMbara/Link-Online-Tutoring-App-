package com.example.link_online_tutoring_app_.FacultiesAcitvities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.link_online_tutoring_app_.ChatActivity;
import com.example.link_online_tutoring_app_.HomeActivity;
import com.example.link_online_tutoring_app_.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CommerceActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commerce);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        //perform item selected
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.private_chat:
                        startActivity(new Intent(CommerceActivity.this, ChatActivity.class));
                        break;

                    case R.id.action_faculties:
                        startActivity(new Intent(CommerceActivity.this,HomeActivity.class));
                        finish();
                        break;
                }


            }
        });
    }


}
