package com.example.link_online_tutoring_app_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.jar.JarException;

import managers.AsyncHTTP;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText std_number= findViewById(R.id.NameEditText);
        final EditText password= findViewById(R.id.PassEditText);
        Button login = findViewById(R.id.button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String stdNumber = std_number.getText().toString().trim();
                final String PassWord = password.getText().toString().trim();
                boolean Alltrue = true;
                if(TextUtils.isEmpty(stdNumber)){
                    Alltrue = false;
                    std_number.setError("Enter Student Number");
                }
                if(TextUtils.isEmpty(stdNumber)){
                    Alltrue = false;
                    password.setError("Enter Password");
                }

                if(Alltrue){
                    ContentValues cv = new ContentValues();
                    cv.put("StudentNumber", stdNumber);
                    cv.put("Password", PassWord);
                    new AsyncHTTP("http://lamp.ms.wits.ac.za/~s1857333/loginuserlink.php", cv){
                        @Override
                        protected void onPreExecute(){

                        }
                        @Override
                        protected void onPostExecute(String output){
                            try{
                                JSONObject ob = new JSONObject(output);
                                String StudentN = ob.getString("STUDENT_NO");
                                startActivity(new Intent(MainActivity.this, HomeActivity.class));

                            } catch (JSONException e){
                                Toast T = Toast.makeText(getApplicationContext(),"Login Failed", Toast.LENGTH_SHORT);
                                T.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START, 240, 0);
                                T.show();
                                e.printStackTrace();
                            }
                        }
                    }.execute();
                }
            }
        });
    }
}
