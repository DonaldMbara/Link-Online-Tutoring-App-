package com.example.link_online_tutoring_app_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {

    Button login_btn,regis_btn;

    EditText std_number,password;
    Button login;
    TextView Register;
    static Context context;
    public static String SHARED_PREF_LOGIN="shared_prefs_login";
    public static String LOGIN_STATUS="login status";
    public static String UN;
    public static String Pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;


        SharedPreferences sharedPreferences1=LoginActivity.context.getSharedPreferences(LoginActivity.SHARED_PREF_LOGIN,Context.MODE_PRIVATE);
        boolean login_status=sharedPreferences1.getBoolean(LOGIN_STATUS,false);
        if(login_status){
            startActivity(new Intent(this,HomeActivity.class));
        }
        else {

            std_number = findViewById(R.id.StudentNoEditText);
            password = findViewById(R.id.PassEditText);
            login = findViewById(R.id.loginBtn);
            Register = findViewById(R.id.RegisterButton);

        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    public void OnClickRegisterHere(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    public void onClickLogin(View view) {
        Log.d("click", "Button Clicked");
        final String stdNumber = std_number.getText().toString().trim();
        UN = std_number.getText().toString().trim();
        final String PassWord = password.getText().toString().trim();
        Pass = password.getText().toString().trim();
        boolean Alltrue = true;
        if (TextUtils.isEmpty(stdNumber)) {
            Alltrue = false;
            std_number.setError("Enter Student Number");
        }
        if (TextUtils.isEmpty(stdNumber)) {
            Alltrue = false;
            password.setError("Enter Password");
        }

        if (Alltrue) {
            //in case all credentials are filled attempt login
            Log.d("checker", "All true, calling request");
            new RequestHandler(getApplicationContext(), "login").execute(stdNumber, PassWord);

        }
    }
}
