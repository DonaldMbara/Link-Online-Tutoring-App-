package com.example.link_online_tutoring_app_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
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
        final EditText std_number= findViewById(R.id.StudentNoEditText);
        final EditText password= findViewById(R.id.PassEditText);
        final Button login = findViewById(R.id.button);
        final Button Register = findViewById(R.id.RegisterButton);

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
                    //in case all credentials are filled attempt login
                   new RequestHandler(getApplicationContext(),"login").execute(stdNumber,PassWord);

                }
            }
        });


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }
}
