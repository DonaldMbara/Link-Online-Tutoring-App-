package com.example.link_online_tutoring_app_;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView my_picture;
    Button scheduleBtn,messagesBtn;
    TextView username,speciality,email,fname,lname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        scheduleBtn=findViewById(R.id.pp_schedule);
        scheduleBtn.setOnClickListener(this);
        messagesBtn=findViewById(R.id.pp_messages);
        messagesBtn.setOnClickListener(this);

        username=findViewById(R.id.pp_username);
        speciality=findViewById(R.id.pp_specializes);
        email=findViewById(R.id.pp_email);
        fname=findViewById(R.id.pp_firstname);
        lname=findViewById(R.id.pp_lastname);

        my_picture=findViewById(R.id.pp_my_image);

        fetchProfile_info();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pp_messages: //in case someone clicks messages button
                //TODO go to chats
                Toast.makeText(getApplicationContext(),"todo go to messages",Toast.LENGTH_SHORT).show();
                break;
            case R.id.pp_schedule:  //in case someone clicks schedule
                //TODO view and modify calender
                Toast.makeText(getApplicationContext(),"This is your calendar",Toast.LENGTH_SHORT).show();
                ProfileActivity.this.startActivity(new Intent(ProfileActivity.this,CalendarActvity.class));
                finish();
        }
    }

    @SuppressLint("StaticFieldLeak")
    public void fetchProfile_info(){
        final String studentNo=getApplicationContext().getSharedPreferences(LoginActivity.SHARED_PREF_LOGIN,MODE_PRIVATE).getString(RequestHandler.Unkey,"-1");
        new AsyncTask<Void, Void, String>(){

            @Override
            protected String doInBackground(Void... voids) {
                try{
                URL url=new URL("https://lamp.ms.wits.ac.za/~s1819369/profile.php");
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();


                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);     //allows us to use input stream
                httpURLConnection.setDoOutput(true);    //allows us to use output stream

                OutputStream outputStream=httpURLConnection.getOutputStream(); //used to write to url
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8")); //used to load text  in buffer and formatting it before being sent

                String data= URLEncoder.encode("studentNo","UTF-8")+"="+URLEncoder.encode(studentNo,"UTF-8");

                bufferedWriter.write(data); //send data to the php file
                bufferedWriter.flush();
                bufferedWriter.close();

                InputStream inputStream=httpURLConnection.getInputStream(); //used to get read data from the url
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                String line="";
                String result="";
                while ((line=bufferedReader.readLine())!=null){
                    //reading the response we got from the php file
                    result+=line;
                }
                //the response is stored in result
                //note that in the case that the response is in jason format you will have to JSON objects

                return result;

            } catch (
            MalformedURLException e) {
                e.printStackTrace();
            } catch (
            IOException e) {
                e.printStackTrace();
            }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject jsonObject=new JSONObject(s);
                    fname.setText(jsonObject.getString("FirstName"));
                    lname.setText(jsonObject.getString("LastName"));
                    username.setText(jsonObject.getString("Username"));
                    email.setText(jsonObject.getString("email"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }
}
