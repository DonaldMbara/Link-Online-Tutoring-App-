package com.example.link_online_tutoring_app_;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.link_online_tutoring_app_.FacultiesAcitvities.CommerceActivity;
import com.example.link_online_tutoring_app_.FacultiesAcitvities.HealthScienceActivity;
import com.example.link_online_tutoring_app_.FacultiesAcitvities.HumanitiesActivity;
import com.example.link_online_tutoring_app_.FacultiesAcitvities.ScienceActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ArrayAdapter<String> arrayAdapter;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listView = findViewById(R.id.listView);
        getJSON("https://lamp.ms.wits.ac.za/~s1819369/get_facul.php");

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem=(String) listView.getItemAtPosition(position);
                Toast.makeText(HomeActivity.this,clickedItem, Toast.LENGTH_LONG).show();

                //perform item selected in the list view
                if(position ==0){ // 0 represents number of position
                    startActivity(new Intent(HomeActivity.this, ScienceActivity.class));
                    finish();
                }
                if(position ==1){
                    startActivity(new Intent(HomeActivity.this, CommerceActivity.class));
                    finish();

                }
                if(position ==2){

                    startActivity(new Intent(HomeActivity.this, HealthScienceActivity.class));
                    finish();
                }
                if(position ==3){
                    startActivity(new Intent(HomeActivity.this, HumanitiesActivity.class));
                    finish();

                }
            }
        });


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

                    case R.id.posting:
                        startActivity(new Intent(HomeActivity.this,PostsActivity.class));
                        finish();
                        break;

                }


            }
        });

    }


    //fetchData, you can make it a class to prevent code reuse
    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json).append("\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] faculties = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            faculties[i] = obj.getString("name");
        }
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, faculties);
        listView.setAdapter(arrayAdapter);
    }
    
}


