package com.example.link_online_tutoring_app_;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ListAllUsers extends AppCompatActivity {

    ArrayAdapter<String> arrayAdapter;
    String[] Name;
    int []studentNum;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_users);

        listView = findViewById(R.id.listView);
        getJSON("https://lamp.ms.wits.ac.za/~s1819369/listAllUsers.php");

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem=(String) listView.getItemAtPosition(position);
                Toast.makeText(ListAllUsers.this,clickedItem, Toast.LENGTH_LONG).show();


                for(int i = 0; i < Name.length; i++){
                    if(position == i){ // i represents number of position
                        listView.setEnabled(false);
                        Intent intent = new Intent(ListAllUsers.this, ChatActivity.class);
                        intent.putExtra("receiver", Name[i]);
                        intent.putExtra("receiver_id",studentNum[i]);
                        startActivity(intent);
                        listView.setEnabled(true);
                    }
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
        Name= new String[jsonArray.length()];
        studentNum= new int[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            Name[i] = obj.getString("Username");
            studentNum[i]=obj.getInt("StudentNo");
        }
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Name);
        listView.setAdapter(arrayAdapter);
    }

}


